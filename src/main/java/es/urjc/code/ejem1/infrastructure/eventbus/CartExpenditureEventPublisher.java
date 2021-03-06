package es.urjc.code.ejem1.infrastructure.eventbus;

import es.urjc.code.ejem1.domain.events.ShoppingCartClosedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CartExpenditureEventPublisher {
    private final ApplicationEventPublisher publisher;

    public CartExpenditureEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(ShoppingCartClosedEvent shoppingCartClosedEvent) {
        publisher.publishEvent(shoppingCartClosedEvent);
    }
}
