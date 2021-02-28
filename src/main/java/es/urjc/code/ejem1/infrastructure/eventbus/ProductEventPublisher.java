package es.urjc.code.ejem1.infrastructure.eventbus;

import es.urjc.code.ejem1.domain.dto.CreateProductDTO;
import es.urjc.code.ejem1.domain.dto.DeleteProductDTO;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ProductEventPublisher {

    private ApplicationEventPublisher publisher;

    public ProductEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(CreateProductDTO productDto) {
        publisher.publishEvent(productDto);
    }

    public void publish(DeleteProductDTO productDto) {
        publisher.publishEvent(productDto);
    }
}
