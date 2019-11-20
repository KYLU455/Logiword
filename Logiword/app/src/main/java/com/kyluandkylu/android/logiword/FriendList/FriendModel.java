package com.kyluandkylu.android.logiword.FriendList;

public class FriendModel {
    private String name;
    private int score;
    private String status;

    public FriendModel(String name, int score, String status){
        this.name = name;
        this.score = score;
        this.status = status;
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public String getStatus(){
        return status;
    }

    public String scoreToString(){
         return String.valueOf(getScore());
    }

}
