package com.kyluandkylu.android.logiword.Game;

public class LetterSetChange implements Move {

    private char letter;

    public LetterSetChange(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }
}
