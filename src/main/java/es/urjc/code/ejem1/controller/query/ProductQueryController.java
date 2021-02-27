package es.urjc.code.ejem1.controller.query;

import es.urjc.code.ejem1.controller.dto.ProductResponseDTO;
import es.urjc.code.ejem1.domain.query.ProductQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("/api/products")
public class ProductQueryController {

    private ProductQueryService productService;
    private ModelMapper mapper = new ModelMapper();


    @GetMapping
    public Collection<ProductResponseDTO> getProducts() {
        return Arrays.asList(mapper.map(productService.getProducts(), ProductResponseDTO[].class));
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProduct(@PathVariable Long id) {
        return mapper.map(productService.getProduct(id), ProductResponseDTO.class);
    }
}
