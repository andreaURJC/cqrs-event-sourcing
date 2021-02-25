package es.urjc.code.ejem1.domain;

import java.util.List;

public class ShoppingCartDTO {

	private Long id;
	private ShoppingCartStatus status;
	private List<ShoppingCartItemDTO> items;
	private double price;

	public ShoppingCartDTO() {
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

	public List<ShoppingCartItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartItemDTO> items) {
		this.items = items;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
