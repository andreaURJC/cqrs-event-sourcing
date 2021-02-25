package es.urjc.code.ejem1.service;

import java.util.List;
import java.util.Random;

import es.urjc.code.ejem1.domain.ShoppingCartItem;
import es.urjc.code.ejem1.domain.ValidationService;

public class ValidationServiceImpl implements ValidationService {

	@Override
	public boolean validate(List<ShoppingCartItem> items) {
		Random rnd = new Random();

		return rnd.nextBoolean();
	}


}
