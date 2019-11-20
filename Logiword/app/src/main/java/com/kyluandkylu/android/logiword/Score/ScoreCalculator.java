package com.kyluandkylu.android.logiword.Score;

import com.kyluandkylu.android.logiword.ViewModel.Calculation;
import com.kyluandkylu.android.logiword.ViewModel.Move;

import java.util.ArrayList;

public class ScoreCalculator {

    public static double calculateScores(ArrayList<Move> moves, int numberOfUnusedLetters, String word) {
        double scores = Math.pow(10, word.toCharArray().length);
        for (Move move : moves) {
            if (move instanceof Calculation) {
                Calculation calculation = (Calculation) move;
                switch (calculation.getType()) {
                    case "<<":
                    case ">>":
                        scores = scores - 1000;
                        break;
                    case "+/-":
                        scores = scores - 2000;
                        break;
                    case "R":
                        break;
                    default:
                        scores = scores - 5000;
                }
            }
        }
        while(numberOfUnusedLetters > 0){
            scores = scores - 10000;
            numberOfUnusedLetters--;
        }
        return scores;
    }
}
