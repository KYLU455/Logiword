package com.kyluandkylu.android.logiword.Profile;

public class ProfileModel {

    private int playerID;
    private String playerName;
    private String playerPassword;
    private String playerEmail;


    public ProfileModel(int playerID, String playerName, String playerPassword, String playerEmail){
        this.playerID = playerID;
        this.playerName = playerName;
        this.playerPassword = playerPassword;
        this.playerEmail = playerEmail;
    }


}
