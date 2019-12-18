package com.kyluandkylu.android.logiword.Authentication;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kyluandkylu.android.logiword.Profile.User;
import com.kyluandkylu.android.logiword.Retrofit.WebService;

import java.util.concurrent.ExecutionException;

public class LogInViewModel extends ViewModel {

    private MutableLiveData<String> buttonPressed;

    public LogInViewModel() {
        buttonPressed = new MutableLiveData<>();
        buttonPressed.setValue("");
    }

    public void pressButton(String buttonName) {
        buttonPressed.setValue(buttonName);
    }

    public boolean isSecondPress() {
        return buttonPressed.getValue().length() != 0;
    }

    private void createUser(String userName, String password, String authToken, Context context) {
        Account account = new Account(userName, "Logi Word Account");
        AccountManager am = AccountManager.get(context);
        am.addAccountExplicitly(account, password, null);
        am.setAuthToken(account, "full_access", authToken);
    }

    public String registerUser(User user, Context context) {
        String errorMessage = checkForInputErrors(user);
        if (errorMessage.length() > 0) {
            return errorMessage;
        }
        try {
            WebService webService = WebService.getInstance();
            webService.registerUser(user);
            Integer token = webService.logIn(user.getUsername(), user.getPassword());
            createUser(user.getUsername(), user.getPassword(), Integer.toString(token), context);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String logInUser(User user, Context context) {
        String errorMessage = checkForInputErrors(user);
        if (errorMessage.length() > 0) {
            return errorMessage;
        }
        WebService webService = WebService.getInstance();
        try {
            Integer token = webService.logIn(user.getUsername(), user.getPassword());
            if (token == null) {
                return "Failed to log in";
            }
            createUser(user.getUsername(), user.getPassword(), Integer.toString(token), context);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String checkForInputErrors(User user) {
        if (user.getUsername().length() == 0) {
            return "Please enter user name";
        }
        if ((user.getMail().length() == 0 || !user.getMail().contains("@") || !user.getMail().contains("."))
                && buttonPressed.getValue().equals("CREATE ACCOUNT")) {
            return "Please enter correct mail address";
        }
        if (user.getPassword().length() < 4) {
            return "Please enter password longer then 4 characters";
        }
        return "";
    }

    public MutableLiveData<String> getButtonPressed() {
        return buttonPressed;
    }
}
