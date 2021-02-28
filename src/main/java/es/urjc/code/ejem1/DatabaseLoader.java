package es.urjc.code.ejem1;

import es.urjc.code.ejem1.domain.dto.FullCartExpenditureDTO;
import es.urjc.code.ejem1.domain.repository.CartExpenditureRepository;
import es.urjc.code.ejem1.infrastructure.entity.ProductEntity;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private SpringDataJPAProductRepository productRepository;

    @Autowired
    private CartExpenditureRepository cartExpenditureRepository;

    ModelMapper mapper = new ModelMapper();

    @Override
    public void run(String... args) throws Exception {

        ProductEntity product1 = new ProductEntity();
        product1.setId(1L);
        product1.setName("PLUMÍFERO MONTAÑA Y SENDERISMO FORCLAZ TREK100 AZUL CAPUCHA");
        product1.setDescription("Esta chaqueta acolchada de plumón y plumas, con certificado RDS, abriga bien durante un vivac entre +5 °C y -5 °C.");
        product1.setPrice(49.99);

        ProductEntity product2 = new ProductEntity();
        product2.setId(2L);
        product2.setName("PANTALÓN RUNNING RUN WARM");
        product2.setDescription("Hemos diseñado este pantalón para los hombres que corren con tiempo frío.");
        product2.setPrice(19.0);

        ProductEntity product3 = new ProductEntity();
        product3.setId(3L);
        product3.setName("ZAPATILLAS RUNNING");
        product3.setDescription("Nuestros equipos de diseño han desarrollado esta zapatilla de running ligera y con amortiguación para correr hasta 10 km a la semana.");
        product3.setPrice(12.48);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        FullCartExpenditureDTO fullCartExpenditureDTO = new FullCartExpenditureDTO(1L, 100.0);
        FullCartExpenditureDTO fullCartExpenditureDTO2 = new FullCartExpenditureDTO(2L, 80.0);

        cartExpenditureRepository.save(fullCartExpenditureDTO);
        cartExpenditureRepository.save(fullCartExpenditureDTO2);

    }

}
