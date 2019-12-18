package com.kyluandkylu.android.logiword.MainMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.kyluandkylu.android.logiword.Game.GameFragment;
import com.kyluandkylu.android.logiword.R;
import com.kyluandkylu.android.logiword.Retrofit.WebService;

import java.util.concurrent.ExecutionException;

public class MainMenu extends Fragment {

    private FragmentTransaction ft;
    private Button singleButton;
    private Button dailyChallengeButton;
    private Button multiPlayerButton;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_menu_fragment, container, false);


        multiPlayerButton = v.findViewById(R.id.multi_player_button);
        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Logi Word");
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
                WebService webService = WebService.getInstance();
                try {
                    String word = webService.getDailyChallengeForToday();
                    ft.replace(R.id.fragment_container, new GameFragment(word)).addToBackStack(null).commit();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        multiPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getContext(), "The Multiplayer is not yet been implemented", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }


}
