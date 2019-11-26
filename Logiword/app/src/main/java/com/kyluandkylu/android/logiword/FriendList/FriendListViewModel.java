package com.kyluandkylu.android.logiword.FriendList;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FriendListViewModel extends ViewModel {
    private MutableLiveData<List<FriendModel>> friendListHolder;

    public FriendListViewModel(){
      //  friendListHolder = new FriendModel("Pista", 456, "online");
        friendListHolder = new MediatorLiveData<>();
        friendListHolder.setValue(new ArrayList<FriendModel>());
        friendListHolder.getValue().add(new FriendModel("Pityu", 4569, "online"));
        friendListHolder.getValue().add(new FriendModel("Pityu2", 4769, "offline"));
        friendListHolder.getValue().add(new FriendModel("Pityu3", 4969, "online"));
        friendListHolder.getValue().add(new FriendModel("Pityu4", 4969, "online"));
        friendListHolder.getValue().add(new FriendModel("Pityu5", 4969, "online"));
        friendListHolder.getValue().add(new FriendModel("Pityu6", 4969, "offline"));
        friendListHolder.getValue().add(new FriendModel("Pityu7", 4969, "online"));
        friendListHolder.getValue().add(new FriendModel("Pityu8", 4969, "offline"));
        friendListHolder.getValue().add(new FriendModel("Pityu9", 4969, "online"));
        friendListHolder.setValue(friendListHolder.getValue());
    }

    public LiveData<List<FriendModel>> getAllFriend() {
        return friendListHolder;
    }

    public void addFriend(String name) {
        friendListHolder.getValue().add(new FriendModel(name, 4569, "offline"));
        friendListHolder.setValue(friendListHolder.getValue());
    }
}
