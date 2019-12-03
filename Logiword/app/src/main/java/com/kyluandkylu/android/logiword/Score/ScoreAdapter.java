package com.kyluandkylu.android.logiword.Score;

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
                .inflate(R.layout.local_score_item, parent, false);
        return new ScoreHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreHolder holder, int position) {
        ScoreModel currentScore = myScores.get(position);
        holder.textViewMyrank.setText(String.valueOf(position + 1));
        holder.textViewMyWord.setText(currentScore.getMyWord());
        holder.textViewMyScore.setText(currentScore.myScoreToString());
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
        private TextView textViewMyrank;
        private TextView textViewMyWord;
        private TextView textViewMyScore;

        public ScoreHolder(View itemView){
            super(itemView);
            textViewMyrank = itemView.findViewById(R.id.local_score_rank);
            textViewMyWord = itemView.findViewById(R.id.local_score_word);
            textViewMyScore = itemView.findViewById(R.id.local_score_myScore);
        }
    }
}
