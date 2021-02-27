package es.urjc.code.ejem1.domain.service;

import es.urjc.code.ejem1.domain.dto.FullCartExpenditureDTO;

import java.util.Collection;

public interface CartExpenditureService {
    public Collection<FullCartExpenditureDTO> getCartExpenditures();
    public FullCartExpenditureDTO createCartExpenditure(FullCartExpenditureDTO fullCartExpenditureDTO);
}
