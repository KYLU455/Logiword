package com.kyluandkylu.android.logiword.GlobalScore;

import com.kyluandkylu.android.logiword.Retrofit.WebService;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {
    private MutableLiveData<List<ScoreModel>> myScoreHolder;
    private WebService webService;

    public ScoreViewModel() throws ExecutionException, InterruptedException {
       if(myScoreHolder != null){
           return;
       }
        myScoreHolder = new MediatorLiveData<>();
        webService = WebService.getInstance();
        myScoreHolder.setValue(webService.getScoreTable());
    }

    public LiveData<List<ScoreModel>> getAllMyScores() {
        return myScoreHolder;
    }

}

