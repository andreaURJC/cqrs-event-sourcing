package es.urjc.code.ejem1.domain.service.query;

import es.urjc.code.ejem1.domain.dto.FullProductDTO;

import java.util.Collection;

public interface ProductQueryService {

    Collection<FullProductDTO> getProducts();
    FullProductDTO getProduct(Long id);
}
