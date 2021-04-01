package spring.react.jwt.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "challenges")
public class Challenge extends BaseEntity{

    private Player challangingPlayer;
    private Player challangedPlayer;
    private boolean isActive;

    public Challenge() {
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "challanging_player_id")
    public Player getChallangingPlayer() {
        return challangingPlayer;
    }

    public void setChallangingPlayer(Player challangingPlayer) {
        this.challangingPlayer = challangingPlayer;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "challanged_player_id")
    public Player getChallangedPlayer() {
        return challangedPlayer;
    }

    public void setChallangedPlayer(Player challangedPlayer) {
        this.challangedPlayer = challangedPlayer;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
