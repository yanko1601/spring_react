package spring.react.jwt.model.dtos;

import com.google.gson.annotations.Expose;

public class CourtAddDto {

    @Expose
    private String city;
    @Expose
    private String complex;
    @Expose
    private int number;

    public CourtAddDto() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplex() {
        return complex;
    }

    public void setComplex(String complex) {
        this.complex = complex;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
