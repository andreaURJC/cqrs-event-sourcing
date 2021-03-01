package es.urjc.code.ejem1.domain.service.command;

import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.domain.dto.ProductDTO;
import es.urjc.code.ejem1.infrastructure.eventbus.ProductEventPublisher;
import es.urjc.code.ejem1.domain.events.ProductCreatedEvent;
import es.urjc.code.ejem1.domain.events.ProductDeletedEvent;
import es.urjc.code.ejem1.infrastructure.exception.ProductNotFoundException;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAProductRepository;
import org.modelmapper.ModelMapper;

import java.util.UUID;

public class ProductCommandServiceImpl implements ProductCommandService {

    private ProductEventPublisher publisher;
    private final SpringDataJPAProductRepository repository;

    ModelMapper mapper = new ModelMapper();

    public ProductCommandServiceImpl(ProductEventPublisher publisher, SpringDataJPAProductRepository repository) {
        this.publisher = publisher;
        this.repository = repository;
    }

    @Override
    public FullProductDTO createProduct(ProductDTO productDTO) {
        productDTO.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        ProductCreatedEvent event = mapper.map(productDTO, ProductCreatedEvent.class);
        publisher.publish(event);

        return mapper.map(productDTO, FullProductDTO.class);
    }

    @Override
    public FullProductDTO deleteProduct(Long id) {
        ProductDeletedEvent event = new ProductDeletedEvent(id);
        FullProductDTO product = mapper.map(repository.findById(id).orElseThrow(ProductNotFoundException::new), FullProductDTO.class);
        publisher.publish(event);

        return product;
    }

}
