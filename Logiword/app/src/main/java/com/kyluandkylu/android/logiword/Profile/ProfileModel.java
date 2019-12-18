package com.kyluandkylu.android.logiword.Profile;

public class ProfileModel {
    private String username;
    private String from;

    public ProfileModel(String username, String from) {
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

}
