package match;

public class SinglePlayerGameModel {
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getWordCreated() {
        return wordCreated;
    }

    public void setWordCreated(String wordCreated) {
        this.wordCreated = wordCreated;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int gameId;
    private int playerId;
    private String wordCreated;
    private int score;
}
