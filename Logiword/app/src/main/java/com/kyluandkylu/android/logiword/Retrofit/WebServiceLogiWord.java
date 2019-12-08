package com.kyluandkylu.android.logiword.Retrofit;

import com.kyluandkylu.android.logiword.GlobalScore.ScoreModel;
import com.kyluandkylu.android.logiword.LocalScore.LocalScoreModel;
import com.kyluandkylu.android.logiword.Profile.ChangeProfileInformationModel;
import com.kyluandkylu.android.logiword.Profile.ProfileModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WebServiceLogiWord {

    @GET("singleplayer")
    Call<List<ScoreModel>> getTopPlayersInSinglePlayer();

    @GET("singleplayer/{myPlayerID}")
    Call<List<LocalScoreModel>> getLocalScoreTable(@Path("myPlayerID") int myPlayerID);

    @POST("account")
    Call<ResponseBody> registerUser(@Body User user);

    @GET("account/{userName}/{password}")
    Call<Integer> logIn(@Path("userName") String userName,@Path("password") String password);

    @POST("singleplayer")
    Call<ResponseBody> sendGameResults(@Body GameResults gameResults);

    @GET("account/{myPlayerID}")
    Call<ProfileModel> getMyProfile(@Path("myPlayerID") int myPlayerID);

    @PUT("account")
    Call<ChangeProfileInformationModel> setNewUserName(@Body ChangeProfileInformationModel changeProfileInformationModel);

    @GET("dailyword")
    Call<ResponseBody> getDailyChallengeForToday();

    @POST("challengeattempt")
    Call<ResponseBody> sendDailyChallengeAttempt(@Body DailyChallengeAttempt dailyChallengeAttempt);
}
