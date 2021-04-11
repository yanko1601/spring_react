package spring.react.jwt.model.dtos;

import com.google.gson.annotations.Expose;

public class ResultSetDto {

    @Expose
    private Long gameId;

    public ResultSetDto() {
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
