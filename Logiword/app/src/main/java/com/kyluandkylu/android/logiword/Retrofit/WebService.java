package com.kyluandkylu.android.logiword.Retrofit;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {

    private WebServiceLogiWord webServiceLogiWord;
    private static final String url = "http://10.0.2.2:8080/";

    public WebService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceLogiWord = retrofit.create(WebServiceLogiWord.class);
    }

    public List<ScoreTableEntity> getScoreTable() throws ExecutionException, InterruptedException {
        return new GetTopPlayersInSinglePlayer().execute(webServiceLogiWord).get();
    }

    private class GetTopPlayersInSinglePlayer extends AsyncTask<WebServiceLogiWord,Void,List<ScoreTableEntity>>{

        @Override
        protected List<ScoreTableEntity> doInBackground(WebServiceLogiWord... webServiceLogiWords) {
            try {
                return webServiceLogiWords[0].getTopPlayersInSinglePlayer().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
