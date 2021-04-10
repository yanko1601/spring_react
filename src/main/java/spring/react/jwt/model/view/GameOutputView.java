package spring.react.jwt.model.view;

public class GameOutputView {

    private Long id;
    private String firstPlayerFullName;
    private String secondPlayerFullName;
    private String city;
    private String place;
    private String time;

    public GameOutputView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstPlayerFullName() {
        return firstPlayerFullName;
    }

    public void setFirstPlayerFullName(String firstPlayerFullName) {
        this.firstPlayerFullName = firstPlayerFullName;
    }

    public String getSecondPlayerFullName() {
        return secondPlayerFullName;
    }

    public void setSecondPlayerFullName(String secondPlayerFullName) {
        this.secondPlayerFullName = secondPlayerFullName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
