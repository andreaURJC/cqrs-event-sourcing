package es.urjc.code.ejem1.infrastructure.eventbus;

import es.urjc.code.ejem1.domain.repository.CartExpenditureRepository;
import es.urjc.code.ejem1.domain.dto.FullCartExpenditureDTO;
import es.urjc.code.ejem1.domain.events.ShoppingCartClosedEvent;
import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CartExpenditureEventListener {
    private final CartExpenditureRepository cartExpenditureRepository;
    private ModelMapper mapper = new ModelMapper();

    public CartExpenditureEventListener(CartExpenditureRepository cartExpenditureRepository) {
        this.cartExpenditureRepository = cartExpenditureRepository;
    }

    @EventListener
    public void saveExpenditure(ShoppingCartClosedEvent shoppingCartClosedEvent) {
        this.cartExpenditureRepository.save(mapper.map(shoppingCartClosedEvent, FullCartExpenditureDTO.class));
    }
}
