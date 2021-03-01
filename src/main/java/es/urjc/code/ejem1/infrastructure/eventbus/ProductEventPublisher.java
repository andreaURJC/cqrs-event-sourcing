package es.urjc.code.ejem1.infrastructure.eventbus;

import es.urjc.code.ejem1.domain.events.ProductCreatedEvent;
import es.urjc.code.ejem1.domain.events.ProductDeletedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ProductEventPublisher {

    private ApplicationEventPublisher publisher;

    public ProductEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(ProductCreatedEvent productCreatedEvent) {
        publisher.publishEvent(productCreatedEvent);
    }

    public void publish(ProductDeletedEvent productDeletedEvent) {
        publisher.publishEvent(productDeletedEvent);
    }
}
