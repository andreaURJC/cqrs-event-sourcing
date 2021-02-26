package es.urjc.code.ejem1.infrastructure;

import es.urjc.code.ejem1.domain.FullCartExpenditureDTO;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CartExpenditureEventPublisher {
    private final ApplicationEventPublisher publisher;

    public CartExpenditureEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(FullCartExpenditureDTO fullCartExpenditureDTO) {
        publisher.publishEvent(fullCartExpenditureDTO);
    }
}
