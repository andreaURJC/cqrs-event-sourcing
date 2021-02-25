package es.urjc.code.ejem1.controller;

import es.urjc.code.ejem1.domain.ShoppingCartStatus;

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
