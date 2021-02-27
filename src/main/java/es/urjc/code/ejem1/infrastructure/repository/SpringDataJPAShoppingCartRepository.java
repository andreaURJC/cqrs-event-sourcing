package es.urjc.code.ejem1.infrastructure.repository;

import es.urjc.code.ejem1.infrastructure.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJPAShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

}
