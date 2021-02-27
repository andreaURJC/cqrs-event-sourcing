package es.urjc.code.ejem1.domain.service.command;

import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.domain.dto.ProductDTO;

public interface ProductCommandService {

	public FullProductDTO createProduct(ProductDTO productDTO);
	public FullProductDTO deleteProduct(Long id);
}
