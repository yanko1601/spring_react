package spring.react.jwt.model.view;

public class PlayerForChallengeListView {

    private Long id;
    private String fullName;
    private int rank;
    private boolean isChallenged;
    private boolean haveMatch;
    private boolean challengedYou;

    public PlayerForChallengeListView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isChallenged() {
        return isChallenged;
    }

    public void setChallenged(boolean challenged) {
        isChallenged = challenged;
    }

    public boolean isHaveMatch() {
        return haveMatch;
    }

    public void setHaveMatch(boolean haveMatch) {
        this.haveMatch = haveMatch;
    }

    public boolean isChallengedYou() {
        return challengedYou;
    }

    public void setChallengedYou(boolean challengedYou) {
        this.challengedYou = challengedYou;
    }
}
