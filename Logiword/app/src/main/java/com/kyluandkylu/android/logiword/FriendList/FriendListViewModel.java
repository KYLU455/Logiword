package com.kyluandkylu.android.logiword.FriendList;

import android.app.Application;

import java.util.Stack;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.kyluandkylu.android.logiword.Authentication.AccountAuthentication;
import com.kyluandkylu.android.logiword.Retrofit.FriendPair;
import com.kyluandkylu.android.logiword.Retrofit.FriendResponse;
import com.kyluandkylu.android.logiword.Retrofit.WebService;

public class FriendListViewModel extends AndroidViewModel {

    private MutableLiveData<String[]> friends;
    private MutableLiveData<Stack<String>> friendRequests;
    private int id;
    private WebService webService;

    public FriendListViewModel(@NonNull Application application) {
        super(application);
        webService = WebService.getInstance();

        friends = new MutableLiveData<>();
        friends.setValue(new String[0]);
        friendRequests = new MutableLiveData<>();
        friendRequests.setValue(new Stack<String>());
        id = Integer.parseInt(AccountAuthentication.getToken(application));
        try {
            friends.setValue(webService.getFriendList(id));
            String [] friendReq = webService.getFriendRequests(id);
            for(String s : friendReq){
                friendRequests.getValue().push(s);
            }
            friendRequests.setValue(friendRequests.getValue());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public MutableLiveData<String[]> getFriends() {
        return friends;
    }

    public MutableLiveData<Stack<String>> getFriendRequests() {
        return friendRequests;
    }

    public void respondToFriendRequest(String status){
        String friendName = friendRequests.getValue().pop();
        WebService webService = WebService.getInstance();
        webService.respondToFriendRequest(new FriendResponse(id,friendName,status));
        friendRequests.setValue(friendRequests.getValue());
        if(status.equals("ACCEPTED")){
            String[] f = new String[friends.getValue().length + 1];
            for(int a = 0; a < friends.getValue().length; a++){
                f[a] = friends.getValue()[a];
            }
            f[friends.getValue().length] = friendName;
            friends.setValue(f);
        }
    }

    public void sendFriendRequest(String friendName){
        webService.sendFriendRequest(new FriendPair(id, friendName));
    }

    public void removeFriend(int index){
        String toRemove = friends.getValue()[index];
        webService.removeFriend(new FriendPair(id, toRemove));
        String[] f = new String[friends.getValue().length - 1];
        for(int a = 0; a < index; a++){
            f[a] = friends.getValue()[a];
        }
        for(int a = index + 1; a < f.length; a++){
            f[a] = friends.getValue()[a];
        }
        friends.setValue(f);
    }
}
