package es.urjc.code.ejem1;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import es.urjc.code.ejem1.domain.FullProductDTO;
import es.urjc.code.ejem1.domain.Product;
import es.urjc.code.ejem1.domain.ProductRepository;

@Controller
public class DatabaseLoader implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;
	ModelMapper mapper = new ModelMapper();

	@Override
	public void run(String... args) throws Exception {

		Product product1 = new Product(
		        "PLUMÍFERO MONTAÑA Y SENDERISMO FORCLAZ TREK100 AZUL CAPUCHA",
		        "Esta chaqueta acolchada de plumón y plumas, con certificado RDS, abriga bien durante un vivac entre +5 °C y -5 °C.",
		        49.99);

		Product product2 = new Product(
		        "PANTALÓN RUNNING RUN WARM",
		        "Hemos diseñado este pantalón para los hombres que corren con tiempo frío.",
		        19.0);

		Product product3 = new Product(
		        "ZAPATILLAS RUNNING",
		        "Nuestros equipos de diseño han desarrollado esta zapatilla de running ligera y con amortiguación para correr hasta 10 km a la semana.",
		        12.48);

		productRepository.save(mapper.map(product1, FullProductDTO.class));
		productRepository.save(mapper.map(product2, FullProductDTO.class));
		productRepository.save(mapper.map(product3, FullProductDTO.class));
	}

}
