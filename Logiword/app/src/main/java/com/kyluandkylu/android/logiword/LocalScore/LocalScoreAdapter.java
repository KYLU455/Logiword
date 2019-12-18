package com.kyluandkylu.android.logiword.LocalScore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kyluandkylu.android.logiword.R;

import java.util.ArrayList;
import java.util.List;

public class LocalScoreAdapter extends RecyclerView.Adapter<LocalScoreAdapter.LocalScoreHolder> {
    private List<LocalScoreModel> myScores = new ArrayList<>();


    @NonNull
    @Override
    public LocalScoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.local_score_item, parent, false);
        return new LocalScoreHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalScoreHolder holder, int position) {
        LocalScoreModel currentScore = myScores.get(position);
        holder.textViewRank.setText(String.valueOf(position + 1));
        holder.textViewWord.setText(currentScore.getWordCreated());
        holder.textViewScore.setText(currentScore.getStringScore());
    }

    @Override
    public int getItemCount() {
        return myScores.size();
    }

    public void setMyScores(List<LocalScoreModel> myScores) {
        this.myScores = myScores;
        notifyDataSetChanged();
    }

    class LocalScoreHolder extends RecyclerView.ViewHolder {
        private TextView textViewRank;
        private TextView textViewWord;
        private TextView textViewScore;

        public LocalScoreHolder(View itemView) {
            super(itemView);
            textViewRank = itemView.findViewById(R.id.local_score_rank);
            textViewWord = itemView.findViewById(R.id.local_score_word);
            textViewScore = itemView.findViewById(R.id.local_score_myScore);

        }
    }
}
