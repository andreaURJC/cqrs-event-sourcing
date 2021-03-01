package es.urjc.code.ejem1.domain.events;

import es.urjc.code.ejem1.domain.dto.FullShoppingCartItemDTO;
import es.urjc.code.ejem1.domain.model.ShoppingCartStatus;

import java.util.List;

public class ShoppingCartSavedEvent {
    private Long id;
    private ShoppingCartStatus status;
    private List<FullShoppingCartItemDTO> items;
    private double price;

    public ShoppingCartSavedEvent() {
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

    public List<FullShoppingCartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<FullShoppingCartItemDTO> items) {
        this.items = items;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
