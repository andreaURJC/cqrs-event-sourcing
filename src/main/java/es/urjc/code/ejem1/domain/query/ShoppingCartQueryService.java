package es.urjc.code.ejem1.domain.query;

import es.urjc.code.ejem1.domain.dto.FullShoppingCartDTO;

public interface ShoppingCartQueryService {

    FullShoppingCartDTO getShoppingCart(Long id);
}
