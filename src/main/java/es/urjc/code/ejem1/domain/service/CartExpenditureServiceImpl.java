package es.urjc.code.ejem1.domain.service;

import es.urjc.code.ejem1.domain.dto.FullCartExpenditureDTO;
import es.urjc.code.ejem1.domain.repository.CartExpenditureRepository;

import java.util.Collection;

public class CartExpenditureServiceImpl implements CartExpenditureService {
    private final CartExpenditureRepository cartExpenditureRepository;

    public CartExpenditureServiceImpl(CartExpenditureRepository cartExpenditureRepository) {
        this.cartExpenditureRepository = cartExpenditureRepository;
    }

    @Override
    public Collection<FullCartExpenditureDTO> getCartExpenditures() {
        return this.cartExpenditureRepository.findAll();
    }

    @Override
    public FullCartExpenditureDTO createCartExpenditure(FullCartExpenditureDTO fullCartExpenditureDTO) {
        return this.cartExpenditureRepository.save(fullCartExpenditureDTO);
    }
}
