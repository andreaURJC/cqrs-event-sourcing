package es.urjc.code.ejem1.domain;

import java.util.List;

public interface ValidationService {

	boolean validate(List<ShoppingCartItem> items);
	
}
