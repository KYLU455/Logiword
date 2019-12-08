package com.kyluandkylu.android.logiword.FriendList;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FriendListViewModel extends ViewModel {

    private MutableLiveData<FriendModel[]> friends;
    private MutableLiveData<Stack<FriendRequest>> friendRequests;

    public FriendListViewModel(){
        friends = new MutableLiveData<>();
        FriendModel[] friendModels = {new FriendModel("MY BEST FRIEND")};
        friends.setValue(friendModels);

        friendRequests = new MutableLiveData<>();
        friendRequests.setValue(new Stack<FriendRequest>());
    }

    public MutableLiveData<FriendModel[]> getFriends() {
        return friends;
    }

    public MutableLiveData<Stack<FriendRequest>> getFriendRequests() {
        return friendRequests;
    }

    public void acceptFriend(){
        FriendRequest friendRequest = friendRequests.getValue().pop();
        friendRequests.setValue(friendRequests.getValue());
    }

    public void rejectFriend(){

    }
}
