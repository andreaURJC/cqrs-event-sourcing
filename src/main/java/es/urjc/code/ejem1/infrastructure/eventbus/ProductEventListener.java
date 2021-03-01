package es.urjc.code.ejem1.infrastructure.eventbus;

import es.urjc.code.ejem1.infrastructure.entity.ProductEntity;
import es.urjc.code.ejem1.domain.events.ProductCreatedEvent;
import es.urjc.code.ejem1.domain.events.ProductDeletedEvent;
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
    public void createProduct(ProductCreatedEvent event) {
        this.repository.save(mapper.map(event, ProductEntity.class));
    }

    @EventListener
    public void deleteProduct(ProductDeletedEvent event) {
        this.repository.deleteById(event.getId());
    }
}
