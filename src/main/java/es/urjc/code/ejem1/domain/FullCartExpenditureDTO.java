package es.urjc.code.ejem1.domain;

public class FullCartExpenditureDTO {

    private int id;
    private double expenditure;

    public FullCartExpenditureDTO() {
        super();
    }

    public FullCartExpenditureDTO(int id, double expenditure) {
        this.id = id;
        this.expenditure = expenditure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }
}
