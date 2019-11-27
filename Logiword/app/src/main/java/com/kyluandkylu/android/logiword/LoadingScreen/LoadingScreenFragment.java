package com.kyluandkylu.android.logiword.LoadingScreen;


import androidx.appcompat.app.ActionBar;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.kyluandkylu.android.logiword.Game.WordList;
import com.kyluandkylu.android.logiword.MainMenu.MainMenu;
import com.kyluandkylu.android.logiword.R;

import java.io.IOException;

public class LoadingScreenFragment extends Fragment {

    private ProgressBar progressBar;
    private Application application;
    private ActionBar actionBar;

    public LoadingScreenFragment(Application application, ActionBar actionBar){
        this.application = application;
        this.actionBar = actionBar;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loading_screen, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);
        final FragmentActivity activity = getActivity();

        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    WordList.wordsTree = WordList.loadWordsFromTextFile(application.getAssets().open("words_alpha.txt"), progressBar);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        actionBar.show();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainMenu()).commit();
                    }
                });
            }
        };
        th.start();
    }
}
