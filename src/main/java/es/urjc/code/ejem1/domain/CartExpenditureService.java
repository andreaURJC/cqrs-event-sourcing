package es.urjc.code.ejem1.domain;

import java.util.Collection;

public interface CartExpenditureService {
    public Collection<FullCartExpenditureDTO> getCartExpenditures();
    public FullCartExpenditureDTO createCartExpenditure(FullCartExpenditureDTO fullCartExpenditureDTO);
}
