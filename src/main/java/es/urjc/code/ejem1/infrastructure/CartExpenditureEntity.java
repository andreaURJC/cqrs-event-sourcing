package es.urjc.code.ejem1.infrastructure;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CartExpenditureEntity {
    @Id
    private int id;

    private double expenditure;

    public CartExpenditureEntity() {}

    public CartExpenditureEntity(int id, double expenditure) {
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
