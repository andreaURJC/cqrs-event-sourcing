package es.urjc.code.ejem1.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Shopping cart not found")
public class ShoppingCartNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5596141541624573125L;

}
