package es.urjc.code.ejem1.controller.dto;

public class CartExpenditureResponseDTO {

    private Long id;
    private double expenditure;

    public CartExpenditureResponseDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }
}
