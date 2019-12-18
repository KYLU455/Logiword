package com.kyluandkylu.android.logiword.LocalScore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kyluandkylu.android.logiword.R;

import java.util.List;

public class LocalScoreFragment extends Fragment {

    private LocalScoreViewModel mViewModel;
    private RecyclerView recyclerView;
    private LocalScoreAdapter localScoreAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.local_score_fragment, container, false);


        recyclerView = v.findViewById(R.id.recycle_view_localScore);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        localScoreAdapter = new LocalScoreAdapter();
        recyclerView.setAdapter(localScoreAdapter);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LocalScoreViewModel.class);
        mViewModel.getAllLocalScores().observe(this, new Observer<List<LocalScoreModel>>() {
            @Override
            public void onChanged(List<LocalScoreModel> localScoreModels) {
                localScoreAdapter.setMyScores(localScoreModels);
            }
        });

    }

}
