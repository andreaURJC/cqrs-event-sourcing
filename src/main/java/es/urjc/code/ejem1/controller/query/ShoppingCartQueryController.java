package es.urjc.code.ejem1.controller.query;

import es.urjc.code.ejem1.controller.dto.ShoppingCartResponseDTO;
import es.urjc.code.ejem1.domain.query.ShoppingCartQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shoppingcarts")
public class ShoppingCartQueryController {

    private ShoppingCartQueryService shoppingService;
    private ModelMapper mapper = new ModelMapper();

    public ShoppingCartQueryController(ShoppingCartQueryService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping("/{id}")
    public ShoppingCartResponseDTO getShoppingCart(@PathVariable Long id) {
        return mapper.map(shoppingService.getShoppingCart(id), ShoppingCartResponseDTO.class);
    }

}
