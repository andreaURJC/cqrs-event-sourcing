package es.urjc.code.ejem1.infrastructure.eventbus;

import es.urjc.code.ejem1.domain.events.ShoppingCartDeletedEvent;
import es.urjc.code.ejem1.domain.events.ShoppingCartSavedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartEventPublisher {
    private ApplicationEventPublisher publisher;

    public ShoppingCartEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(ShoppingCartSavedEvent event) {
        publisher.publishEvent(event);
    }

    public void delete(ShoppingCartDeletedEvent event) {
        publisher.publishEvent(event);
    }

}
