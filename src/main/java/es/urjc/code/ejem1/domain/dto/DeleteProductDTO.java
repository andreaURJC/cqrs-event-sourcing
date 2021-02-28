package es.urjc.code.ejem1.domain.dto;

public class DeleteProductDTO {

    Long id;

    public DeleteProductDTO() {
    }

    public DeleteProductDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
