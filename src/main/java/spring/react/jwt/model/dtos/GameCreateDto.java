package spring.react.jwt.model.dtos;

import com.google.gson.annotations.Expose;

public class GameCreateDto {

    @Expose
    private Long firstPlayerId;
    @Expose
    private Long secondPlayerId;

    public GameCreateDto() {
    }

    public Long getFirstPlayerId() {
        return firstPlayerId;
    }

    public void setFirstPlayerId(Long firstPlayerId) {
        this.firstPlayerId = firstPlayerId;
    }

    public Long getSecondPlayerId() {
        return secondPlayerId;
    }

    public void setSecondPlayerId(Long secondPlayerId) {
        this.secondPlayerId = secondPlayerId;
    }
}
