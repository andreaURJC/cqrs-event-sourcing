package es.urjc.code.ejem1.controller;

import java.util.List;

import es.urjc.code.ejem1.domain.ShoppingCartStatus;

public class ShoppingCartResponseDTO {

	private Long id;
	private ShoppingCartStatus status;
	private List<ShoppingCartItemResponseDTO> items;
	private double price;

	public ShoppingCartResponseDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ShoppingCartStatus getStatus() {
		return status;
	}

	public void setStatus(ShoppingCartStatus status) {
		this.status = status;
	}

	public List<ShoppingCartItemResponseDTO> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartItemResponseDTO> items) {
		this.items = items;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
