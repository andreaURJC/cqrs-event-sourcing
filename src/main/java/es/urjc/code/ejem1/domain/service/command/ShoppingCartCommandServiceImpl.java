package es.urjc.code.ejem1.domain.service.command;

import es.urjc.code.ejem1.domain.dto.*;
import es.urjc.code.ejem1.domain.events.ShoppingCartDeletedEvent;
import es.urjc.code.ejem1.domain.events.ShoppingCartSavedEvent;
import es.urjc.code.ejem1.domain.service.ValidationService;
import es.urjc.code.ejem1.domain.model.Product;
import es.urjc.code.ejem1.domain.model.ShoppingCart;
import es.urjc.code.ejem1.domain.model.ShoppingCartItem;
import es.urjc.code.ejem1.domain.model.ShoppingCartStatus;
import es.urjc.code.ejem1.infrastructure.eventbus.CartExpenditureEventPublisher;
import es.urjc.code.ejem1.infrastructure.eventbus.ShoppingCartEventPublisher;
import es.urjc.code.ejem1.domain.events.ShoppingCartClosedEvent;
import es.urjc.code.ejem1.infrastructure.exception.ProductNotFoundException;
import es.urjc.code.ejem1.infrastructure.exception.ShoppingCartNotFoundException;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAShoppingCartRepository;
import org.modelmapper.ModelMapper;

import java.util.UUID;

public class ShoppingCartCommandServiceImpl implements ShoppingCartCommandService {

    private SpringDataJPAShoppingCartRepository shoppingCartRepository;
    private SpringDataJPAProductRepository productRepository;
    private ValidationService validationService;
    private CartExpenditureEventPublisher cartExpenditureEventPublisher;
    private ShoppingCartEventPublisher publisher;


    private ModelMapper mapper = new ModelMapper();
    long id = 1L;

    public ShoppingCartCommandServiceImpl(SpringDataJPAShoppingCartRepository shoppingCartRepository,
                                          SpringDataJPAProductRepository productRepository,
                                          ValidationService validationService,
                                          CartExpenditureEventPublisher cartExpenditureEventPublisher,
                                          ShoppingCartEventPublisher publisher) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.validationService = validationService;
        this.cartExpenditureEventPublisher = cartExpenditureEventPublisher;
        this.publisher = publisher;
    }

    private FullShoppingCartDTO saveShoppingCart(SaveShoppingCartDTO saveShoppingCartDTO) {
        publisher.publish(mapper.map(saveShoppingCartDTO, ShoppingCartSavedEvent.class));

        return mapper.map(saveShoppingCartDTO, FullShoppingCartDTO.class);
    }

    @Override
    public FullShoppingCartDTO createShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        SaveShoppingCartDTO saveShoppingCartDTO = mapper.map(shoppingCart, SaveShoppingCartDTO.class);

        return saveShoppingCart(saveShoppingCartDTO);
    }

    @Override
    public FullShoppingCartDTO updateShoppingCart(Long id, ShoppingCartDTO shoppingCartDTO) {
        FullShoppingCartDTO fullShoppingCartDTO = mapper.map(shoppingCartRepository.findById(id).orElseThrow(() -> new ShoppingCartNotFoundException()), FullShoppingCartDTO.class);

        ShoppingCart shoppingCart = mapper.map(fullShoppingCartDTO, ShoppingCart.class);
        ShoppingCart updateShoppingCart = mapper.map(shoppingCartDTO, ShoppingCart.class);

        if (updateShoppingCart.getStatus() != null &&
                updateShoppingCart.getStatus() == ShoppingCartStatus.COMPLETED) {
            shoppingCart.setValidationService(validationService);
            shoppingCart.validate();
        }

        SaveShoppingCartDTO newShoppingCartDTO = mapper.map(shoppingCart, SaveShoppingCartDTO.class);
        FullShoppingCartDTO savedFullShoppingCartDTO = saveShoppingCart(newShoppingCartDTO);

        if (shoppingCart.isCompleted()) {
            this.cartExpenditureEventPublisher.publish(new ShoppingCartClosedEvent(savedFullShoppingCartDTO.getId(), savedFullShoppingCartDTO.getPrice()));
        }

        return savedFullShoppingCartDTO;
    }

    @Override
    public FullShoppingCartDTO deleteShoppingCart(Long id) {
        FullShoppingCartDTO fullShoppingCartDTO = mapper.map(shoppingCartRepository.findById(id).orElseThrow(ProductNotFoundException::new), FullShoppingCartDTO.class);
        publisher.delete(new ShoppingCartDeletedEvent(id));

        return fullShoppingCartDTO;
    }

    @Override
    public FullShoppingCartDTO addProduct(Long idShoppingCart, Long idProduct, int quantity) {
        FullProductDTO fullProductDTO = mapper.map(productRepository.findById(idProduct)
                .orElseThrow(ProductNotFoundException::new), FullProductDTO.class);
        FullShoppingCartDTO fullShoppingCartDTO = mapper.map(shoppingCartRepository.findById(idShoppingCart)
                .orElseThrow(ShoppingCartNotFoundException::new), FullShoppingCartDTO.class);

        return addProduct(fullProductDTO, fullShoppingCartDTO, quantity);
    }

    public FullShoppingCartDTO addProduct(FullProductDTO fullProductDTO, FullShoppingCartDTO fullShoppingCartDTO,
                                          int quantity) {
        ShoppingCart shoppingCart = mapper.map(fullShoppingCartDTO, ShoppingCart.class);
        shoppingCart.removeItem(fullProductDTO.getId());

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem(
                mapper.map(fullProductDTO, Product.class),
                quantity);
        shoppingCart.addItem(shoppingCartItem);

        SaveShoppingCartDTO newFullProductDTO = mapper.map(shoppingCart, SaveShoppingCartDTO.class);

        return saveShoppingCart(newFullProductDTO);
    }

    @Override
    public FullShoppingCartDTO deleteProduct(Long idShoppingCart, Long idProduct) {
        FullShoppingCartDTO fullShoppingCartDTO = mapper.map(shoppingCartRepository.findById(idShoppingCart).orElseThrow(() -> new ProductNotFoundException()), FullShoppingCartDTO.class);

        ShoppingCart shoppingCart = mapper.map(fullShoppingCartDTO, ShoppingCart.class);
        shoppingCart.removeItem(idProduct);

        SaveShoppingCartDTO newFullProductDTO = mapper.map(shoppingCart, SaveShoppingCartDTO.class);

        return saveShoppingCart(newFullProductDTO);
    }
}
