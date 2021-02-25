package es.urjc.code.ejem1.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ShoppingCartDontStockException extends RuntimeException {

	private static final long serialVersionUID = -5596141541624573125L;

	public ShoppingCartDontStockException(String message) {
		super(message);
	}
}
