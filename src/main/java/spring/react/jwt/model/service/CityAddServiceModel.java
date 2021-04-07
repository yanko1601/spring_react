package spring.react.jwt.model.service;

public class CityAddServiceModel {

    private String name;

    public CityAddServiceModel() {
    }

    public CityAddServiceModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
