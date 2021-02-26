package es.urjc.code.ejem1.infrastructure;

import es.urjc.code.ejem1.domain.CartExpenditureRepository;
import es.urjc.code.ejem1.domain.FullCartExpenditureDTO;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CartExpenditureEventListener {
    private final CartExpenditureRepository cartExpenditureRepository;

    public CartExpenditureEventListener(CartExpenditureRepository cartExpenditureRepository) {
        this.cartExpenditureRepository = cartExpenditureRepository;
    }

    @EventListener
    public void saveExpenditure(FullCartExpenditureDTO fullCartExpenditureDTO) {
        this.cartExpenditureRepository.save(fullCartExpenditureDTO);
    }
}
