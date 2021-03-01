package es.urjc.code.ejem1.infrastructure.eventbus;

import es.urjc.code.ejem1.domain.events.ShoppingCartDeletedEvent;
import es.urjc.code.ejem1.domain.events.ShoppingCartSavedEvent;
import es.urjc.code.ejem1.infrastructure.entity.ShoppingCartEntity;
import es.urjc.code.ejem1.infrastructure.repository.SpringDataJPAShoppingCartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartEventListener {

    private final SpringDataJPAShoppingCartRepository shoppingCartRepository;
    private final ModelMapper mapper = new ModelMapper();

    public ShoppingCartEventListener(SpringDataJPAShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @EventListener
    private void createShoppingCart(ShoppingCartSavedEvent event) {
        this.shoppingCartRepository.save(mapper.map(event, ShoppingCartEntity.class));
    }

    @EventListener
    private void deleteShoppingCart(ShoppingCartDeletedEvent event) {
        this.shoppingCartRepository.deleteById(event.getId());
    }

}
