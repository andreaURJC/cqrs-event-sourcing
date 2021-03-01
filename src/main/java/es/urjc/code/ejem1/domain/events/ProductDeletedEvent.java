package es.urjc.code.ejem1.domain.events;

public class ProductDeletedEvent {

    Long id;

    public ProductDeletedEvent() {
    }

    public ProductDeletedEvent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
