package com.kyluandkylu.android.logiword.Score;

public class ScoreModel {
    private int gameID;
    private int playerID;
    private String myWord;
    private int myScore;


    public ScoreModel(String myWord, int myScore){
        this.myWord = myWord;
        this.myScore = myScore;
    }

    public String getMyWord() {
        return myWord;
    }

    public int getMyScore() {
        return myScore;
    }

    public String myScoreToString(){
        return String.valueOf(getMyScore());
}


}
