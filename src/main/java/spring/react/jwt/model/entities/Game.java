package spring.react.jwt.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "games")
public class Game extends BaseEntity{

    private LocalDateTime dateTime;
    private boolean isFinished;
    private int winnerGames;
    private int looserGames;
    private Player firstPlayer;
    private Player secondPlayer;
    private Player winner;
    private Player looser;
    private Court court;

    public Game() {
    }

    @Column(name = "date_time")
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Column(name = "is_finished")
    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Column(name = "winner_games")
    public int getWinnerGames() {
        return winnerGames;
    }

    public void setWinnerGames(int winnerGames) {
        this.winnerGames = winnerGames;
    }

    @Column(name = "looser_games")
    public int getLooserGames() {
        return looserGames;
    }

    public void setLooserGames(int looserGames) {
        this.looserGames = looserGames;
    }

    @ManyToOne()
    @JoinColumn(name = "firstPl_id", referencedColumnName = "id")
    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    @ManyToOne()
    @JoinColumn(name = "secondPl_id", referencedColumnName = "id")
    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    @ManyToOne()
    @JoinColumn(name = "winner_id", referencedColumnName = "id")
    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    @ManyToOne()
    @JoinColumn(name = "looser_id", referencedColumnName = "id")
    public Player getLooser() {
        return looser;
    }

    public void setLooser(Player looser) {
        this.looser = looser;
    }

    @ManyToOne()
    @JoinColumn(name = "court_id", referencedColumnName = "id")
    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }
}
