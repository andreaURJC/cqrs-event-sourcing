package es.urjc.code.ejem1.infrastructure.repository;

import es.urjc.code.ejem1.infrastructure.entity.CartExpenditureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJPACartExpenditureRepository extends JpaRepository<CartExpenditureEntity, Integer> {
}
