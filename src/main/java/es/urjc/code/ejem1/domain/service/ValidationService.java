package es.urjc.code.ejem1.domain.service;

import es.urjc.code.ejem1.domain.model.ShoppingCartItem;

import java.util.List;

public interface ValidationService {

	boolean validate(List<ShoppingCartItem> items);
	
}
