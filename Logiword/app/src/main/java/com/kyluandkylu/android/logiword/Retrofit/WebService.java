package com.kyluandkylu.android.logiword.Retrofit;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kyluandkylu.android.logiword.FriendList.FriendPairModel;
import com.kyluandkylu.android.logiword.FriendList.FriendResponseModel;
import com.kyluandkylu.android.logiword.Game.DailyChallengeAttemptModel;
import com.kyluandkylu.android.logiword.Game.GameResultsModel;
import com.kyluandkylu.android.logiword.GlobalScore.ScoreModel;
import com.kyluandkylu.android.logiword.LocalScore.LocalScoreModel;
import com.kyluandkylu.android.logiword.Profile.ChangeProfileInformationModel;
import com.kyluandkylu.android.logiword.Profile.ProfileModel;
import com.kyluandkylu.android.logiword.Profile.User;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {

    private WebServiceLogiWord webServiceLogiWord;
    private static WebService webService;
    private static final String url = "http://10.0.2.2:8080/";

    private WebService() {
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

    public ProfileModel getMyProfileInformation(int myPlayerID) throws ExecutionException, InterruptedException {
        Object[] params = {webServiceLogiWord, myPlayerID};
        return new GetMyProfileInformation().execute(params).get();
    }

    public void sendGameResults(final GameResultsModel gameResultsModel) {
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    webServiceLogiWord.sendGameResults(gameResultsModel).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
    }


    public void changeUserName(ChangeProfileInformationModel changeProfileInformationModel) throws ExecutionException, InterruptedException {
        Call<ChangeProfileInformationModel> call = webServiceLogiWord.setNewUserName(changeProfileInformationModel);

        call.enqueue(new Callback<ChangeProfileInformationModel>() {
            @Override
            public void onResponse(Call<ChangeProfileInformationModel> call, Response<ChangeProfileInformationModel> response) {
                ChangeProfileInformationModel userResponse = response.body();
            }

            @Override
            public void onFailure(Call<ChangeProfileInformationModel> call, Throwable t) {

            }
        });
    }

    public String getDailyChallengeForToday() throws ExecutionException, InterruptedException {
        return new GetDailyChallengeForToday().execute(webServiceLogiWord).get();
    }

    public void sendDailyChallengeAttempt(final DailyChallengeAttemptModel dailyChallengeAttemptModel) {
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    webServiceLogiWord.sendDailyChallengeAttempt(dailyChallengeAttemptModel).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
    }

    public String[] getFriendList(int id) throws ExecutionException, InterruptedException {
        Object[] params = {webServiceLogiWord, id};
        return new GetFriendList().execute(params).get();
    }

    public String[] getFriendRequests(int id) throws ExecutionException, InterruptedException {
        Object[] params = {webServiceLogiWord, id};
        return new GetFriendRequests().execute(params).get();
    }

    public void sendFriendRequest(final FriendPairModel friendPairModel) {
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    webServiceLogiWord.sendFriendRequest(friendPairModel).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
    }

    public void respondToFriendRequest(final FriendResponseModel friendResponseModel) {
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    webServiceLogiWord.respondToFriendRequest(friendResponseModel).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
    }

    public void removeFriend(final FriendPairModel friendPairModel) {
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    webServiceLogiWord.removeFriend(friendPairModel).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
    }

    private class GetFriendRequests extends AsyncTask<Object, Void, String[]> {

        @Override
        protected String[] doInBackground(Object... objects) {
            WebServiceLogiWord webServiceLogiWord = (WebServiceLogiWord) objects[0];
            Integer id = (Integer) objects[1];
            try {
                return webServiceLogiWord.getFriendRequests(id).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class GetFriendList extends AsyncTask<Object, Void, String[]> {

        @Override
        protected String[] doInBackground(Object... objects) {
            WebServiceLogiWord webServiceLogiWord = (WebServiceLogiWord) objects[0];
            Integer id = (Integer) objects[1];
            try {
                return webServiceLogiWord.getFriendList(id).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
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

    private class GetMyProfileInformation extends AsyncTask<Object, Void, ProfileModel> {

        @Override
        protected ProfileModel doInBackground(Object... objects) {
            WebServiceLogiWord webServiceLogiWord = (WebServiceLogiWord) objects[0];
            Integer playerId = (Integer) objects[1];
            try {
                return webServiceLogiWord.getMyProfile(playerId).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class GetDailyChallengeForToday extends AsyncTask<WebServiceLogiWord, Void, String> {

        @Override
        protected String doInBackground(WebServiceLogiWord... webServiceLogiWords) {
            try {
                return webServiceLogiWords[0].getDailyChallengeForToday().execute().body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}