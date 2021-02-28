package es.urjc.code.ejem1.domain.dto;

public class DeleteShoppingCartDto {
    private Long id;

    public DeleteShoppingCartDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
