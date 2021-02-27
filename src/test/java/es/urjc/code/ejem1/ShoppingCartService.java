package es.urjc.code.ejem1;

import es.urjc.code.ejem1.domain.command.ProductCommandServiceImpl;
import es.urjc.code.ejem1.domain.command.ShoppingCartCommandServiceImpl;
import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.domain.dto.FullShoppingCartDTO;
import es.urjc.code.ejem1.domain.dto.FullShoppingCartItemDTO;
import es.urjc.code.ejem1.domain.dto.ProductDTO;
import es.urjc.code.ejem1.domain.model.Product;
import es.urjc.code.ejem1.infrastructure.entity.ProductEntity;
import es.urjc.code.ejem1.infrastructure.entity.ShoppingCartEntity;
import es.urjc.code.ejem1.infrastructure.eventbus.CartExpenditureEventPublisher;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAShoppingCartRepository;
import es.urjc.code.ejem1.service.ValidationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.modelmapper.ModelMapper;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@TestMethodOrder(OrderAnnotation.class)
public class ShoppingCartService {

    private SpringDataJPAProductRepository productRepository;
    private ProductCommandServiceImpl productService;

    private SpringDataJPAShoppingCartRepository shoppingCartRepository;
    private ShoppingCartCommandServiceImpl shoppingCartService;

    private CartExpenditureEventPublisher publisher;

    private ModelMapper mapper = new ModelMapper();

    private static FullShoppingCartDTO createdShoppingCart;

    @BeforeEach
    void setUp() {
        productRepository = mock(SpringDataJPAProductRepository.class);
        shoppingCartRepository = mock(SpringDataJPAShoppingCartRepository.class);
        publisher = mock(CartExpenditureEventPublisher.class);

        productService = new ProductCommandServiceImpl(productRepository);
        shoppingCartService = new ShoppingCartCommandServiceImpl(
                shoppingCartRepository,
                productRepository,
                new ValidationServiceImpl(),
                publisher);
    }

    @Test
    @Order(1)
    void shoppingCartCanBeAdded() {
        createdShoppingCart = shoppingCartService.createShoppingCart();
        verify(shoppingCartRepository).save(mapper.map(createdShoppingCart, ShoppingCartEntity.class));
    }

    @Test
    @Order(2)
    void productCanBeAddedToShoppingCart() {
        Product product = new Product(
                "PLUMÍFERO MONTAÑA Y SENDERISMO FORCLAZ TREK100 AZUL CAPUCHA",
                "Esta chaqueta acolchada de plumón y plumas, con certificado RDS, abriga bien durante un vivac entre +5 °C y -5 °C.",
                49.99);
        ProductDTO productDTO = mapper.map(product, ProductDTO.class);

        FullProductDTO fullProductDTO = productService.createProduct(productDTO);
        verify(productRepository).save(mapper.map(fullProductDTO, ProductEntity.class));

        int items = Math.abs(new Random().nextInt());

        createdShoppingCart = shoppingCartService.addProduct(fullProductDTO, createdShoppingCart, items);
        FullShoppingCartItemDTO fullShoppingCartItemDTO = createdShoppingCart.getItems().get(0);

        assertEquals(fullShoppingCartItemDTO.getQuantity(), items);
        assertEquals(fullShoppingCartItemDTO.getTotalPrice(), items * productDTO.getPrice());
    }

    @Test
    @Order(3)
    void shoppingCartCanBeDeleted() {
        shoppingCartService.deleteShoppingCart(createdShoppingCart.getId());
        verify(shoppingCartRepository).deleteById(createdShoppingCart.getId());
    }
}
