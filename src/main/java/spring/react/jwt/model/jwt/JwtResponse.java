package spring.react.jwt.model.jwt;

import spring.react.jwt.model.view.PlayerOutputView;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final PlayerOutputView loggedPlayer;

    public JwtResponse(String jwttoken, PlayerOutputView loggedPlayer) {
        this.jwttoken = jwttoken;
        this.loggedPlayer = loggedPlayer;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public PlayerOutputView getPlayerOutputView() {
        return loggedPlayer;
    }
}
