package spring.react.jwt.model.dtos;

import com.google.gson.annotations.Expose;

public class ChallengeSetDto {

    @Expose
    private Long challengingPlayerId;
    @Expose
    private Long challengedPlayerId;

    public ChallengeSetDto() {
    }

    public Long getChallengingPlayerId() {
        return challengingPlayerId;
    }

    public void setChallengingPlayerId(Long challengingPlayerId) {
        this.challengingPlayerId = challengingPlayerId;
    }

    public Long getChallengedPlayerId() {
        return challengedPlayerId;
    }

    public void setChallengedPlayerId(Long challengedPlayerId) {
        this.challengedPlayerId = challengedPlayerId;
    }
}
