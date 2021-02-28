package es.urjc.code.ejem1.infrastructure.eventbus;

import es.urjc.code.ejem1.domain.dto.DeleteShoppingCartDto;
import es.urjc.code.ejem1.domain.dto.SaveShoppingCartDTO;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartEventPublisher {
    private ApplicationEventPublisher publisher;

    public ShoppingCartEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(SaveShoppingCartDTO saveShoppingCartDTO) {
        publisher.publishEvent(saveShoppingCartDTO);
    }

    public void delete(DeleteShoppingCartDto deleteShoppingCartDto) {
        publisher.publishEvent(deleteShoppingCartDto);
    }

}
