package es.urjc.code.ejem1.domain.repository;

import es.urjc.code.ejem1.domain.dto.FullCartExpenditureDTO;

import java.util.Collection;

public interface CartExpenditureRepository {
    Collection<FullCartExpenditureDTO> findAll();

    FullCartExpenditureDTO save(FullCartExpenditureDTO fullCartExpenditureDTO);
}
