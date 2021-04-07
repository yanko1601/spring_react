package spring.react.jwt.model.dtos;

import com.google.gson.annotations.Expose;

public class CityAddDto {

    @Expose
    private String name;

    public CityAddDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
