package spring.react.jwt.model.view;

public class GameFinishedOutputView {

    private Long id;
    private String firstPlayerFullName;
    private String secondPlayerFullName;
    private int firstGames;
    private int secondGames;

    public GameFinishedOutputView() {
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

    public int getFirstGames() {
        return firstGames;
    }

    public void setFirstGames(int firstGames) {
        this.firstGames = firstGames;
    }

    public int getSecondGames() {
        return secondGames;
    }

    public void setSecondGames(int secondGames) {
        this.secondGames = secondGames;
    }
}
