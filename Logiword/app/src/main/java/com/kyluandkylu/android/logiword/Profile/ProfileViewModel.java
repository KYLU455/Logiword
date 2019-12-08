package com.kyluandkylu.android.logiword.Profile;

import android.app.Application;
import android.util.Log;

import com.kyluandkylu.android.logiword.Authentication.AccountAuthentication;
import com.kyluandkylu.android.logiword.Retrofit.WebService;

import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

public class ProfileViewModel extends AndroidViewModel {
    private MutableLiveData<ProfileModel> myProfile;
    private MutableLiveData<ChangeProfileInformationModel> myUsername;
    private WebService webService;

    public ProfileViewModel(@NonNull Application context) throws ExecutionException, InterruptedException {
        super(context);
        if (myProfile != null) {
            return;
        }
        myProfile = new MediatorLiveData<>();
        webService = WebService.getInstance();
        myProfile.setValue(webService.getMyProfileInformation(Integer.parseInt(AccountAuthentication.getToken(context))));

    }

    public LiveData<ProfileModel> getMyProfileInformation() {
        return myProfile;

    }

    public void setMyUsername(ChangeProfileInformationModel changeProfileInformationModel) throws ExecutionException, InterruptedException {
        webService.changeUserName(changeProfileInformationModel);
    }
}
