package com.kyluandkylu.android.logiword.LocalScore;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyluandkylu.android.logiword.R;

public class LocalScoreFragment extends Fragment {

    private LocalScoreViewModel mViewModel;

    public static LocalScoreFragment newInstance() {
        return new LocalScoreFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.local_score_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LocalScoreViewModel.class);
        // TODO: Use the ViewModel
    }

}
