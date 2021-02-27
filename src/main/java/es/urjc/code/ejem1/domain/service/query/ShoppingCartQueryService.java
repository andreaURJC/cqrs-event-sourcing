package es.urjc.code.ejem1.domain.service.query;

import es.urjc.code.ejem1.domain.dto.FullShoppingCartDTO;

public interface ShoppingCartQueryService {

    FullShoppingCartDTO getShoppingCart(Long id);
}
