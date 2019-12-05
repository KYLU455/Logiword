package com.kyluandkylu.android.logiword.GlobalScore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kyluandkylu.android.logiword.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreHolder> {
    private List<ScoreModel> myScores = new ArrayList<>();

    @NonNull
    @Override
    public ScoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.global_score_item, parent, false);
        return new ScoreHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreHolder holder, int position) {
        ScoreModel currentScore = myScores.get(position);
        holder.textViewRank.setText(String.valueOf(position + 1));
        holder.textViewName.setText(currentScore.getPlayerName());
        holder.textViewWord.setText(currentScore.getWordCreated());
        holder.textViewScore.setText(currentScore.getStringScore());
    }

    @Override
    public int getItemCount() {
        return myScores.size();
    }

    public void setMyScores(List<ScoreModel> myScores){
        this.myScores = myScores;
        notifyDataSetChanged();

    }


    class ScoreHolder extends RecyclerView.ViewHolder{
        private TextView textViewRank;
        private TextView textViewWord;
        private TextView textViewScore;
        private TextView textViewName;

        public ScoreHolder(View itemView){
            super(itemView);
            textViewRank = itemView.findViewById(R.id.global_score_rank);
            textViewName = itemView.findViewById(R.id.global_score_name);
            textViewWord = itemView.findViewById(R.id.global_score_word);
            textViewScore = itemView.findViewById(R.id.global_score_myScore);

        }
    }
}
