package spring.react.jwt.model.service;

import com.google.gson.annotations.Expose;

public class CourtAddServiceModel {

    private String city;
    private String complex;
    private int number;

    public CourtAddServiceModel() {
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
