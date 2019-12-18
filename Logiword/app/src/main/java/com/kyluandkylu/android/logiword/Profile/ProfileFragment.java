package com.kyluandkylu.android.logiword.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kyluandkylu.android.logiword.Authentication.AccountAuthentication;
import com.kyluandkylu.android.logiword.R;
import com.kyluandkylu.android.logiword.Retrofit.WebService;

import java.util.concurrent.ExecutionException;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;

    private TextView myName;
    private TextView myRegistrationDate;
    private EditText newUsername;
    private Button changeUsername;
    WebService webService;
    String temporaryUsername;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.profile_fragment, container, false);
        myName = v.findViewById(R.id.my_profile_name);
        myRegistrationDate = v.findViewById(R.id.my_profile_from);
        newUsername = v.findViewById(R.id.my_profile_username_edit_text);
        changeUsername = v.findViewById(R.id.my_profile_change_username_button);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        mViewModel.getMyProfileInformation().observe(this, new Observer<ProfileModel>() {
            @Override
            public void onChanged(ProfileModel profileModel) {
                myName.setText("Name: " + profileModel.getUsername());
                myRegistrationDate.setText("Registered at: " + profileModel.getFrom());
                temporaryUsername = profileModel.getUsername();
            }
        });

        changeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webService = WebService.getInstance();
                try {
                    String newUserName = newUsername.getText().toString();
                    if (!newUserName.equals(temporaryUsername)) {
                        mViewModel.setMyUsername(new ChangeProfileInformationModel(Integer.parseInt(AccountAuthentication.getToken(getContext())), newUserName));
                        Toast toast = Toast.makeText(getContext(), "The username is changed to " + newUserName, Toast.LENGTH_LONG);
                        toast.show();

                        myName.setText("Name: " + newUserName);
                        temporaryUsername = newUserName;
                    } else {
                        Toast toast2 = Toast.makeText(getContext(), "You typed the same username", Toast.LENGTH_LONG);
                        toast2.show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
