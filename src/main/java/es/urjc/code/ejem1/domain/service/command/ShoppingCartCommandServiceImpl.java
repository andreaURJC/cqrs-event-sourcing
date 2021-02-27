package es.urjc.code.ejem1.domain.service.command;

import es.urjc.code.ejem1.domain.service.ValidationService;
import es.urjc.code.ejem1.domain.dto.FullCartExpenditureDTO;
import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.domain.dto.FullShoppingCartDTO;
import es.urjc.code.ejem1.domain.dto.ShoppingCartDTO;
import es.urjc.code.ejem1.domain.model.Product;
import es.urjc.code.ejem1.domain.model.ShoppingCart;
import es.urjc.code.ejem1.domain.model.ShoppingCartItem;
import es.urjc.code.ejem1.domain.model.ShoppingCartStatus;
import es.urjc.code.ejem1.infrastructure.entity.ProductEntity;
import es.urjc.code.ejem1.infrastructure.eventbus.CartExpenditureEventPublisher;
import es.urjc.code.ejem1.infrastructure.entity.ShoppingCartEntity;
import es.urjc.code.ejem1.infrastructure.exception.ProductNotFoundException;
import es.urjc.code.ejem1.infrastructure.exception.ShoppingCartNotFoundException;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAShoppingCartRepository;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class ShoppingCartCommandServiceImpl implements ShoppingCartCommandService {

    private SpringDataJPAShoppingCartRepository shoppingCartRepository;
    private SpringDataJPAProductRepository productRepository;
    private ValidationService validationService;
    private CartExpenditureEventPublisher cartExpenditureEventPublisher;

    private ModelMapper mapper = new ModelMapper();

    public ShoppingCartCommandServiceImpl(SpringDataJPAShoppingCartRepository shoppingCartRepository,
                                          SpringDataJPAProductRepository productRepository,
                                          ValidationService validationService,
                                          CartExpenditureEventPublisher cartExpenditureEventPublisher) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.validationService = validationService;
        this.cartExpenditureEventPublisher = cartExpenditureEventPublisher;
    }

    private FullShoppingCartDTO saveShoppingCart(FullShoppingCartDTO fullShoppingCartDTO) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.save(mapper.map(fullShoppingCartDTO, ShoppingCartEntity.class));
        FullShoppingCartDTO saveFullShoppingCartDTO = mapper.map(shoppingCartEntity, FullShoppingCartDTO.class);

        return saveFullShoppingCartDTO;
    }

    @Override
    public FullShoppingCartDTO createShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        FullShoppingCartDTO fullShoppingCartDTO = mapper.map(shoppingCart, FullShoppingCartDTO.class);

        return saveShoppingCart(fullShoppingCartDTO);
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

        FullShoppingCartDTO newShoppingCartDTO = mapper.map(shoppingCart, FullShoppingCartDTO.class);
        FullShoppingCartDTO savedFullShoppingCartDTO = saveShoppingCart(newShoppingCartDTO);

        if (shoppingCart.isCompleted()) {
            this.cartExpenditureEventPublisher.publish(new FullCartExpenditureDTO(savedFullShoppingCartDTO.getId(), savedFullShoppingCartDTO.getPrice()));
        }

        return savedFullShoppingCartDTO;
    }

    @Override
    public FullShoppingCartDTO deleteShoppingCart(Long id) {
        FullShoppingCartDTO fullShoppingCartDTO = mapper.map(shoppingCartRepository.findById(id).orElseThrow(() -> new ProductNotFoundException()), FullShoppingCartDTO.class);
        shoppingCartRepository.deleteById(id);

        return fullShoppingCartDTO;
    }

    @Override
    public FullShoppingCartDTO addProduct(Long idShoppingCart, Long idProduct, int quantity) {
        FullProductDTO fullProductDTO = mapper.map(productRepository.findById(idProduct)
                .orElseThrow(() -> new ProductNotFoundException()), FullProductDTO.class);
        FullShoppingCartDTO fullShoppingCartDTO = mapper.map(shoppingCartRepository.findById(idShoppingCart)
                .orElseThrow(() -> new ShoppingCartNotFoundException()), FullShoppingCartDTO.class);

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

        FullShoppingCartDTO newFullProductDTO = mapper.map(shoppingCart, FullShoppingCartDTO.class);

        return saveShoppingCart(newFullProductDTO);
    }

    @Override
    public FullShoppingCartDTO deleteProduct(Long idShoppingCart, Long idProduct) {
        FullShoppingCartDTO fullShoppingCartDTO = mapper.map(shoppingCartRepository.findById(idShoppingCart).orElseThrow(() -> new ProductNotFoundException()), FullShoppingCartDTO.class);

        ShoppingCart shoppingCart = mapper.map(fullShoppingCartDTO, ShoppingCart.class);
        shoppingCart.removeItem(idProduct);

        FullShoppingCartDTO newFullProductDTO = mapper.map(shoppingCart, FullShoppingCartDTO.class);

        return saveShoppingCart(newFullProductDTO);
    }
}
