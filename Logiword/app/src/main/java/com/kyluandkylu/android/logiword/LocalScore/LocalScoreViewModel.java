package com.kyluandkylu.android.logiword.LocalScore;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.kyluandkylu.android.logiword.Authentication.AccountAuthentication;
import com.kyluandkylu.android.logiword.Retrofit.WebService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LocalScoreViewModel extends AndroidViewModel {
    private MutableLiveData<List<LocalScoreModel>> myScoreHolder;
    private WebService webService;

    public LocalScoreViewModel(Application context) throws ExecutionException, InterruptedException {
        super(context);
        if (myScoreHolder != null) {
            return;
        }
        myScoreHolder = new MediatorLiveData<>();
        webService = WebService.getInstance();
        myScoreHolder.setValue(webService.getLocalScoreTable(Integer.parseInt(AccountAuthentication.getToken(context))));
    }

    public LiveData<List<LocalScoreModel>> getAllLocalScores() {
        return myScoreHolder;
    }
}
