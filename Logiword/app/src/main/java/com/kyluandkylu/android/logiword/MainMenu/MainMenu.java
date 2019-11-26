package com.kyluandkylu.android.logiword.MainMenu;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kyluandkylu.android.logiword.R;
import com.kyluandkylu.android.logiword.Game.GameFragment;

public class MainMenu extends Fragment {

    private MainMenuViewModel mViewModel;
    private FragmentTransaction ft;
    private Button singleButton;
    private Button dailyChallengeButton;

    public static MainMenu newInstance() {
        return new MainMenu();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_menu_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainMenuViewModel.class);
        // TODO: Use the ViewModel
        //toolbar.findViewById(R.id.toolbar);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ft = getFragmentManager().beginTransaction();
        singleButton = view.findViewById(R.id.single_player_button);
        singleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                ft.replace(R.id.fragment_container, new GameFragment()).addToBackStack(null).commit();
            }
        });

        dailyChallengeButton = view.findViewById(R.id.daily_challenge_button);
        dailyChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                // TODO: 20-Nov-19 add get day
                ft.replace(R.id.fragment_container, new GameFragment("day")).addToBackStack(null).commit();
            }
        });
    }


}
