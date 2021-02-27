package es.urjc.code.ejem1.service;

import es.urjc.code.ejem1.domain.dto.FullCartExpenditureDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class ExpenditureChecker {

    public static final Logger log = LoggerFactory.getLogger(ExpenditureChecker.class);
    final String CART_EXPENDITURE_QUEUE = "cartExpenditureQueue";

    @StreamListener(CART_EXPENDITURE_QUEUE)
    public void saveCartExpenditure(FullCartExpenditureDTO cartExpenditureDTO) {
        log.info("Recibo de la cola: {}, {}", cartExpenditureDTO.getId(), cartExpenditureDTO.getExpenditure());
    }
}
