package com.kyluandkylu.android.logiword.Retrofit;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.kyluandkylu.android.logiword.GlobalScore.ScoreModel;
import com.kyluandkylu.android.logiword.LocalScore.LocalScoreModel;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {

    private WebServiceLogiWord webServiceLogiWord;
    private static WebService webService;
    private static final String url = "http://10.0.2.2:8080/";

    private WebService(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        webServiceLogiWord = retrofit.create(WebServiceLogiWord.class);
    }

    public static WebService getInstance() {
        if (webService == null) {
            webService = new WebService();
        }
        return webService;
    }

    public List<ScoreModel> getScoreTable() throws ExecutionException, InterruptedException {
        return new GetTopPlayersInSinglePlayer().execute(webServiceLogiWord).get();
    }

    public Integer logIn(String userName, String password) throws ExecutionException, InterruptedException {
        Object[] params = {webServiceLogiWord, userName, password};
        return new LogIn().execute(params).get();
    }

    public ResponseBody registerUser(User user) throws ExecutionException, InterruptedException {
        Object[] params = {webServiceLogiWord, user};
        return new RegisterUser().execute(params).get();
    }

    public List<LocalScoreModel> getLocalScoreTable(int myPlayerID) throws ExecutionException, InterruptedException {
        Object[] params = {webServiceLogiWord, myPlayerID};
        return new GetPrivateScoresForSinglePlayer().execute(params).get();
    }

    public void sendGameResults(final GameResults gameResults){
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    webServiceLogiWord.sendGameResults(gameResults).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
    }


    private class GetTopPlayersInSinglePlayer extends AsyncTask<WebServiceLogiWord, Void, List<ScoreModel>> {

        @Override
        protected List<ScoreModel> doInBackground(WebServiceLogiWord... webServiceLogiWords) {
            try {
                return webServiceLogiWords[0].getTopPlayersInSinglePlayer().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class RegisterUser extends AsyncTask<Object, Void, ResponseBody> {

        @Override
        protected ResponseBody doInBackground(Object... objects) {
            WebServiceLogiWord webServiceLogiWord = (WebServiceLogiWord) objects[0];
            User user = (User) objects[1];
            try {
                return webServiceLogiWord.registerUser(user).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class LogIn extends AsyncTask<Object, Void, Integer> {
        @Override
        protected Integer doInBackground(Object... objects) {
            WebServiceLogiWord webServiceLogiWord = (WebServiceLogiWord) objects[0];
            String userName = (String) objects[1];
            String password = (String) objects[2];
            try {
                return webServiceLogiWord.logIn(userName, password).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class GetPrivateScoresForSinglePlayer extends AsyncTask<Object, Void, List<LocalScoreModel>> {


        @Override
        protected List<LocalScoreModel> doInBackground(Object... objects) {
            WebServiceLogiWord webServiceLogiWord = (WebServiceLogiWord) objects[0];
            Integer playerId = (Integer) objects[1];
            try {
                return webServiceLogiWord.getLocalScoreTable(playerId).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}