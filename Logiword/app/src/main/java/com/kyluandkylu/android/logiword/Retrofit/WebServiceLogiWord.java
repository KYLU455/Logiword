package com.kyluandkylu.android.logiword.Retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServiceLogiWord {

    @GET("singleplayer")
    Call<List<ScoreTableEntity>> getTopPlayersInSinglePlayer();
}
