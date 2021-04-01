package spring.react.jwt.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "courts")
public class Court extends BaseEntity{

    private City city;
    private String complex;
    private int courtNumber;

    public Court() {
    }

    @ManyToOne
    @JoinColumn(name = "city_id")
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Column(name = "complex")
    public String getComplex() {
        return complex;
    }

    public void setComplex(String complex) {
        this.complex = complex;
    }

    @Column(name = "court_number")
    public int getCourtNumber() {
        return courtNumber;
    }

    public void setCourtNumber(int courtNumber) {
        this.courtNumber = courtNumber;
    }
}
