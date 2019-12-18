package com.kyluandkylu.android.logiword.Authentication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kyluandkylu.android.logiword.MainMenu.MainMenu;
import com.kyluandkylu.android.logiword.R;
import com.kyluandkylu.android.logiword.Profile.User;

import java.sql.Timestamp;
import java.util.Date;


public class LogInFragment extends Fragment {

    private LogInViewModel logInViewModel;

    private EditText editTextMail;
    private EditText editTextPassword;
    private EditText editTextUserName;
    private Button buttonCreateAccount;
    private Button buttonLogIn;
    private TextView textViewUserName;
    private TextView textViewPassword;
    private TextView textViewMail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        logInViewModel = ViewModelProviders.of(this).get(LogInViewModel.class);

        editTextMail = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextUserName = view.findViewById(R.id.editTextUserName);
        buttonCreateAccount = view.findViewById(R.id.buttonCreateAccount);
        buttonLogIn = view.findViewById(R.id.buttonLogIn);
        textViewUserName = view.findViewById(R.id.textViewUserName);
        textViewMail = view.findViewById(R.id.textViewEmail);
        textViewPassword = view.findViewById(R.id.textViewPassword);

        logInViewModel.getButtonPressed().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (logInViewModel.getButtonPressed().getValue().length() > 0) {
                    editTextPassword.setVisibility(View.VISIBLE);
                    editTextUserName.setVisibility(View.VISIBLE);
                    textViewUserName.setVisibility(View.VISIBLE);
                    textViewPassword.setVisibility(View.VISIBLE);
                    if (logInViewModel.getButtonPressed().getValue().equals("CREATE ACCOUNT")) {
                        editTextMail.setVisibility(View.VISIBLE);
                        textViewMail.setVisibility(View.VISIBLE);
                        buttonLogIn.setVisibility(View.GONE);
                    } else {
                        buttonCreateAccount.setVisibility(View.GONE);
                    }
                } else {
                    editTextPassword.setVisibility(View.GONE);
                    editTextUserName.setVisibility(View.GONE);
                    textViewUserName.setVisibility(View.GONE);
                    textViewPassword.setVisibility(View.GONE);
                    editTextMail.setVisibility(View.GONE);
                    textViewMail.setVisibility(View.GONE);
                    buttonLogIn.setVisibility(View.VISIBLE);
                    buttonCreateAccount.setVisibility(View.VISIBLE);
                }
            }
        });

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton("CREATE ACCOUNT", view);
            }
        });

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressButton("LOG IN", view);
            }
        });

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && logInViewModel.isSecondPress()) {
                    logInViewModel.getButtonPressed().setValue("");
                    return true;
                }
                return false;
            }
        });
    }

    private void displayErrorMessage(String errorMessage) {
        new AlertDialog.Builder(getContext())
                .setTitle("Registration error")
                .setMessage(errorMessage)
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    private User readUserData() {
        String userName = editTextUserName.getText().toString();
        String mail = editTextMail.getText().toString();
        String password = editTextPassword.getText().toString();
        Timestamp date = new Timestamp(new Date().getTime());
        return new User(-1, userName, password, mail, date);
    }

    private void moveToMenu(View view) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainMenu()).commit();
    }

    private void pressButton(String buttonText, View view) {
        if (logInViewModel.isSecondPress()) {
            String errorMessage;
            if (buttonText.equals("CREATE ACCOUNT")) {
                errorMessage = logInViewModel.registerUser(readUserData(), getContext());
            } else {
                errorMessage = logInViewModel.logInUser(readUserData(), getContext());
            }
            if (errorMessage.length() > 0) {
                displayErrorMessage(errorMessage);
            } else {
                moveToMenu(view);
            }
        } else {
            logInViewModel.pressButton(buttonText);
        }
    }
}