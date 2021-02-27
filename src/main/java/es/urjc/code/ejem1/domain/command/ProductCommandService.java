package es.urjc.code.ejem1.domain.command;

import es.urjc.code.ejem1.domain.dto.FullProductDTO;
import es.urjc.code.ejem1.domain.dto.ProductDTO;

import java.util.Collection;

public interface ProductCommandService {

	public FullProductDTO createProduct(ProductDTO productDTO);
	public FullProductDTO deleteProduct(Long id);
}
