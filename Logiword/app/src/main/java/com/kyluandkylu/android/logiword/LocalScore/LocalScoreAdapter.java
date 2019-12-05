package com.kyluandkylu.android.logiword.LocalScore;

import android.view.View;
import android.widget.TextView;

import com.kyluandkylu.android.logiword.R;

import androidx.recyclerview.widget.RecyclerView;

//public class LocalScoreAdapter extends RecyclerView {








    class LocalScoreHolder extends RecyclerView.ViewHolder{
        private TextView textViewRank;
        private TextView textViewWord;
        private TextView textViewScore;
        private TextView textViewName;

        public LocalScoreHolder(View itemView){
            super(itemView);
            textViewRank = itemView.findViewById(R.id.global_score_rank);
            textViewName = itemView.findViewById(R.id.global_score_name);
            textViewWord = itemView.findViewById(R.id.global_score_word);
            textViewScore = itemView.findViewById(R.id.global_score_myScore);

        }
}
