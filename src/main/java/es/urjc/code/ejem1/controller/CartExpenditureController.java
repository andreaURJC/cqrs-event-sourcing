package es.urjc.code.ejem1.controller;

import es.urjc.code.ejem1.controller.dto.CartExpenditureResponseDTO;
import es.urjc.code.ejem1.domain.service.CartExpenditureService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("/api/cartexpenditure")
public class CartExpenditureController {

    private final CartExpenditureService cartExpenditureService;
    private ModelMapper mapper = new ModelMapper();

    public CartExpenditureController(CartExpenditureService cartExpenditureService) {
        this.cartExpenditureService = cartExpenditureService;
    }

    @GetMapping
    public Collection<CartExpenditureResponseDTO> getCartExpenditures() {
        return Arrays.asList(mapper.map(cartExpenditureService.getCartExpenditures(), CartExpenditureResponseDTO[].class));
    }
}
