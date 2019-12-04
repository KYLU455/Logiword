package com.kyluandkylu.android.logiword.Score;

import com.kyluandkylu.android.logiword.FriendList.FriendModel;
import com.kyluandkylu.android.logiword.Retrofit.WebService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {
    private MutableLiveData<List<ScoreModel>> myScoreHolder;

    public ScoreViewModel() {
        //  friendListHolder = new FriendModel("Pista", 456, "online");
        WebService webService = new WebService();
        try {
            webService.getScoreTable();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myScoreHolder = new MediatorLiveData<>();
        myScoreHolder.setValue(new ArrayList<ScoreModel>());
        myScoreHolder.getValue().add(new ScoreModel("Pityu", 4569));
        myScoreHolder.getValue().add(new ScoreModel("Pityu2", 4769));
        myScoreHolder.getValue().add(new ScoreModel("Pityu3", 4969));
        myScoreHolder.getValue().add(new ScoreModel("Pityu4", 4969));
        myScoreHolder.getValue().add(new ScoreModel("Pityu5", 4969));
        myScoreHolder.getValue().add(new ScoreModel("Pityu6", 4969));
        myScoreHolder.getValue().add(new ScoreModel("Pityu7", 4969));
        myScoreHolder.getValue().add(new ScoreModel("Pityu8", 4969));
        myScoreHolder.getValue().add(new ScoreModel("Pityu9", 4969));
        myScoreHolder.setValue(myScoreHolder.getValue());
    }

    public LiveData<List<ScoreModel>> getAllMyScores() {
        return myScoreHolder;
    }

}

