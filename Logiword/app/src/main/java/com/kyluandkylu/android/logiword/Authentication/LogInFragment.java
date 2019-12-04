package com.kyluandkylu.android.logiword.Authentication;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.kyluandkylu.android.logiword.MainMenu.MainMenu;
import com.kyluandkylu.android.logiword.R;
import com.kyluandkylu.android.logiword.Retrofit.User;
import com.kyluandkylu.android.logiword.Retrofit.WebService;

import java.sql.Date;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;


public class LogInFragment extends Fragment {

    private EditText editTextMail;
    private EditText editTextPassword;
    private EditText editTextUserName;
    private Button buttonLogIn;
    private Button buttonContinue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextMail = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextUserName = view.findViewById(R.id.editTextUserName);
        buttonLogIn = view.findViewById(R.id.buttonLogIn);
        buttonContinue = view.findViewById(R.id.buttonContinueWithout);

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String errorMessage = "";
                String userName = editTextUserName.getText().toString();
                if(userName.length() == 0){
                    errorMessage = "Please enter user name";
                }
                String mail = editTextMail.getText().toString();
                if(mail.length() == 0 || !mail.contains("@") || !mail.contains(".")){
                    errorMessage = "Please enter correct mail address";
                }
                String password = editTextPassword.getText().toString();
                if(userName.length() < 4){
                    errorMessage = "Please enter password longer then 4 characters";
                }

                if(errorMessage.length() > 0){
                    new AlertDialog.Builder(getContext())
                            .setTitle("Registration error")
                            .setMessage(errorMessage)
                            .setIcon(R.drawable.ic_warning_black_24dp)
                            .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }else {
                    try {
                        WebService webService = new WebService();
                        Date date = new Date(Calendar.getInstance().getTime().getTime());
                        User user = new User(-1,userName,password,mail,date);
                        webService.registerUser(user);
                        Integer token = webService.logIn(userName, password);
                        createUser(editTextMail.getText().toString(), editTextPassword.getText().toString(), Integer.toString(token));
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainMenu()).commit();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainMenu()).commit();
            }
        });
    }

    private void createUser(String userName, String password, String authToken){
        Account account = new Account(userName, "Logi Word Account");

        AccountManager am = AccountManager.get(getContext());
        am.addAccountExplicitly(account, password, null);
        am.setAuthToken(account, "full_access", authToken);
    }
}
