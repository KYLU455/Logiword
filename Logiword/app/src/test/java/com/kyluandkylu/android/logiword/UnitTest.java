package com.kyluandkylu.android.logiword;

import com.kyluandkylu.android.logiword.Game.Calculation;
import com.kyluandkylu.android.logiword.Game.LetterSetChange;
import com.kyluandkylu.android.logiword.Game.Move;
import com.kyluandkylu.android.logiword.GlobalScore.ScoreCalculator;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class UnitTest {

    @Test
    public void scoreSystemTest(){
        ArrayList<Move> fakeMoves = new ArrayList<>();
        fakeMoves.add(new Calculation("+", 6 , 6));
        fakeMoves.add(new Calculation("+", 12 , 1));
        fakeMoves.add(new LetterSetChange('M'));
        fakeMoves.add(new LetterSetChange('A'));
        fakeMoves.add(new Calculation("+", 6 , 6));
        fakeMoves.add(new Calculation("CE", 12, 0));
        fakeMoves.add(new Calculation("^", 8, 8));
        fakeMoves.add(new Calculation("<<", 16777216, 1677721));
        fakeMoves.add(new Calculation("<<", 1677721, 16777));
        fakeMoves.add(new Calculation("<<", 16777, 1677));
        fakeMoves.add(new Calculation("<<", 1677, 167));
        fakeMoves.add(new Calculation("<<", 167, 16));
        fakeMoves.add(new Calculation("-", 16, 2));
        fakeMoves.add(new LetterSetChange('N'));
        assertEquals(-45000 , (int) ScoreCalculator.calculateScores(fakeMoves,1, "MAN",1));
    }
}

