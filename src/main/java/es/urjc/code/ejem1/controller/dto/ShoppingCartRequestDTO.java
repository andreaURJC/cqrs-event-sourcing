package es.urjc.code.ejem1.controller.dto;

import es.urjc.code.ejem1.domain.model.ShoppingCartStatus;

public class ShoppingCartRequestDTO {
	
	private ShoppingCartStatus status;

	public ShoppingCartRequestDTO() {
		super();
	}

	public ShoppingCartStatus getStatus() {
		return status;
	}

	public void setStatus(ShoppingCartStatus status) {
		this.status = status;
	}

}
