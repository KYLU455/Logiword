package com.kyluandkylu.android.logiword.Profile;

public class ProfileModel {

    private String username;
    private String from;


    public ProfileModel(String username, String mail, String from){
        this.username = username;
        this.from = from;
    }

    public String getUsername() {
        return username;
    }

    public String getFrom() {
        from = from.substring(0, 10);
        return from;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyAll();
    }


    public void setFrom(String from) {
        this.from = from;
        notifyAll();
    }
}
