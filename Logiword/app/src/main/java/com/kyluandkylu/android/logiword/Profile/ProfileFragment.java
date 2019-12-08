package com.kyluandkylu.android.logiword.Profile;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kyluandkylu.android.logiword.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    private TextView myName;
    private TextView myRegistrationDate;
    private EditText newUsername;
    private Button changeUsername;

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
            }
        });
    }

}
