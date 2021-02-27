package es.urjc.code.ejem1.domain.service.command;

import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.domain.dto.FullShoppingCartDTO;
import es.urjc.code.ejem1.domain.dto.ShoppingCartDTO;

public interface ShoppingCartCommandService {

    FullShoppingCartDTO createShoppingCart();

    FullShoppingCartDTO updateShoppingCart(Long id, ShoppingCartDTO shoppingCartDTO);

    FullShoppingCartDTO deleteShoppingCart(Long id);

    FullShoppingCartDTO addProduct(Long idShoppingCart, Long idProduct, int nProducts);

    FullShoppingCartDTO addProduct(FullProductDTO fullProductDTO, FullShoppingCartDTO fullShoppingCartDTO, int quantity);

    FullShoppingCartDTO deleteProduct(Long idShoppingCart, Long idProduct);
}
