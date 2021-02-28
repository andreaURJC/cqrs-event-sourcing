package es.urjc.code.ejem1.infrastructure.eventbus;

import es.urjc.code.ejem1.domain.dto.CreateProductDTO;
import es.urjc.code.ejem1.domain.dto.DeleteProductDTO;
import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.infrastructure.entity.ProductEntity;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListener {

    private SpringDataJPAProductRepository repository;

    ModelMapper mapper = new ModelMapper();

    public ProductEventListener(SpringDataJPAProductRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void createProduct(CreateProductDTO productDTO) {
        this.repository.save(mapper.map(productDTO, ProductEntity.class));
    }

    @EventListener
    public void deleteProduct(DeleteProductDTO productDTO) {
        this.repository.deleteById(productDTO.getId());
    }
}
