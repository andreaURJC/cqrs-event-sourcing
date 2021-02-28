package es.urjc.code.ejem1.infrastructure.eventbus;

import es.urjc.code.ejem1.domain.dto.SaveShoppingCartDTO;
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
    private void createShoppingCart(SaveShoppingCartDTO saveShoppingCartDTO) {
        this.shoppingCartRepository.save(mapper.map(saveShoppingCartDTO, ShoppingCartEntity.class));
    }

}
