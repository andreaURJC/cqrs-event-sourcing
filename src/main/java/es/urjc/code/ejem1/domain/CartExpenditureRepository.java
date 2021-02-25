package es.urjc.code.ejem1.domain;

import java.util.Collection;

public interface CartExpenditureRepository {
    Collection<FullCartExpenditureDTO> findAll();

    FullCartExpenditureDTO save(FullCartExpenditureDTO fullCartExpenditureDTO);
}
