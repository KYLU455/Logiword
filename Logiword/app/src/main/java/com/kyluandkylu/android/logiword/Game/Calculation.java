package com.kyluandkylu.android.logiword.Game;

public class Calculation implements Move {

    private String type;
    private int valueBefore;
    private int coefficient;

    public Calculation(String type, int valueBefore, int coefficient) {
        this.type = type;
        this.valueBefore = valueBefore;
        this.coefficient = coefficient;
    }

    public String getType() {
        return type;
    }

    public int getValueBefore() {
        return valueBefore;
    }

    public int getCoefficient() {
        return coefficient;
    }
}
