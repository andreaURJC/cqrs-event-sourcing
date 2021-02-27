package es.urjc.code.ejem1.domain.dto;

public class FullCartExpenditureDTO {

    private Long id;
    private Double expenditure;

    public FullCartExpenditureDTO() {
        super();
    }

    public FullCartExpenditureDTO(Long id, Double expenditure) {
        this.id = id;
        this.expenditure = expenditure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
    }
}
