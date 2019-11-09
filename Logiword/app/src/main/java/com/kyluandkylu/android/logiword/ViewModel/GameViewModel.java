package com.kyluandkylu.android.logiword.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class GameViewModel extends ViewModel {

    private MutableLiveData<Integer> currentVal;
    private MutableLiveData<String> currentValText;
    private MutableLiveData<String> currentLetters;
    private ArrayList<Move> moves;

    public GameViewModel(){
        currentVal = new MutableLiveData<>();
        currentVal.setValue(0);
        currentValText = new MutableLiveData<>();
        currentValText.setValue("0");
        currentLetters = new MutableLiveData<>();
        currentLetters.setValue("Your Letters");
        moves = new ArrayList<>();
    }

    public MutableLiveData<String> getCurrentLetters() {
        return currentLetters;
    }

    public void addLetter(char letter){
        String current = currentLetters.getValue();
        if (current.equals("Your Letters")){
            currentLetters.setValue((letter + "").toUpperCase());
        }else {
            currentLetters.setValue(current + " " + letter);
        }
    }

    public MutableLiveData<String> getCurrentValText(){
        return currentValText;
    }

    public void changeCurrentValText(String newText){
        currentValText.setValue(newText);
    }

    public LiveData<Integer> getCurrentVal(){
        return currentVal;
    }

    public void changeVal(int newVal){
        currentVal.setValue(newVal);
    }

    public void negateVal(){
        currentVal.setValue(currentVal.getValue() * -1);
    }

    public void performOperation(String type, int val){
        int v = currentVal.getValue();
        switch (type){
            case "+":
                currentVal.setValue(v + val);
                break;
            case "-":
                currentVal.setValue(v - val);
                break;
            case "*":
                currentVal.setValue(v * val);
                break;
            case "/":
                currentVal.setValue(v / val);
                break;
            case "^":
                currentVal.setValue(v ^ val);
                break;
        }
        moves.add(new Calculation(type, v, val));
    }
}
