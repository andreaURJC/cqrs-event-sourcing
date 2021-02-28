package es.urjc.code.ejem1;

import es.urjc.code.ejem1.domain.dto.CreateProductDTO;
import es.urjc.code.ejem1.domain.dto.DeleteProductDTO;
import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.domain.dto.ProductDTO;
import es.urjc.code.ejem1.domain.model.Product;
import es.urjc.code.ejem1.domain.service.command.ProductCommandServiceImpl;
import es.urjc.code.ejem1.infrastructure.eventbus.ProductEventPublisher;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@TestMethodOrder(OrderAnnotation.class)
public class ProductService {

	private ProductEventPublisher publisher;
	private ProductCommandServiceImpl productService;
	private SpringDataJPAProductRepository productRepository;

	private ModelMapper mapper = new ModelMapper();

	private static FullProductDTO createdProduct;

	@BeforeEach
	void setUp() {
		publisher = mock(ProductEventPublisher.class);
		productService = new ProductCommandServiceImpl(publisher, productRepository);
	}

	@Test
	@Order(1)
	void productCanBeAdded() {
		Product product = new Product(
		        "PLUMÍFERO MONTAÑA Y SENDERISMO FORCLAZ TREK100 AZUL CAPUCHA",
		        "Esta chaqueta acolchada de plumón y plumas, con certificado RDS, abriga bien durante un vivac entre +5 °C y -5 °C.",
		        49.99);

		ProductDTO productDTO = mapper.map(product, ProductDTO.class);

		createdProduct = productService.createProduct(productDTO);
		verify(publisher).publish(mapper.map(createdProduct, CreateProductDTO.class));
	}

	@Test
	@Order(2)
	void productCanBeDeleted() {
		productService.deleteProduct(createdProduct.getId());
		verify(publisher).publish(new DeleteProductDTO(createdProduct.getId()));
	}
}
