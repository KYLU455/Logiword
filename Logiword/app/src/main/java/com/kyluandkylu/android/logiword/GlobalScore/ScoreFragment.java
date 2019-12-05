package com.kyluandkylu.android.logiword.GlobalScore;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyluandkylu.android.logiword.R;

import java.util.List;

public class ScoreFragment extends Fragment {

    private ScoreViewModel mViewModel;
    private RecyclerView recyclerView;
    private ScoreAdapter scoreAdapter;

    public static ScoreFragment newInstance() {
        return new ScoreFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.score_fragment, container, false);

        recyclerView = v.findViewById(R.id.recycle_view_score);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        scoreAdapter = new ScoreAdapter();
        recyclerView.setAdapter(scoreAdapter);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ScoreViewModel.class);
       // mViewModel = ViewModelProviders.of(this).get(ScoreViewModel.class);
        mViewModel.getAllMyScores().observe(this, new Observer<List<ScoreModel>>() {
            @Override
            public void onChanged(List<ScoreModel> scoreModels) {
                scoreAdapter.setMyScores(scoreModels);
            }
        });
    }

}
