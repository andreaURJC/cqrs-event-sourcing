package es.urjc.code.ejem1.domain.events;

public class ShoppingCartDeletedEvent {
    private Long id;

    public ShoppingCartDeletedEvent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
