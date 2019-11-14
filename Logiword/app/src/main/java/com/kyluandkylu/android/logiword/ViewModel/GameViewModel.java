package com.kyluandkylu.android.logiword.ViewModel;

import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class GameViewModel extends ViewModel {

    private MutableLiveData<Integer> currentVal;
    private MutableLiveData<String> currentValText;
    private MutableLiveData<ArrayList<Character>> currentLetters;
    private MutableLiveData<String> currentWord;
    private ArrayList<Move> moves;

    public GameViewModel(){
        currentVal = new MutableLiveData<>();
        currentVal.setValue(0);
        currentValText = new MutableLiveData<>();
        currentValText.setValue("0");
        currentLetters = new MutableLiveData<>();
        currentLetters.setValue(new ArrayList<Character>());
        moves = new ArrayList<>();
    }

    public MutableLiveData<ArrayList<Character>> getCurrentLetters() {
        return currentLetters;
    }

    public void addLetter(char letter){
        currentLetters.getValue().add(letter);
        currentLetters.setValue(currentLetters.getValue());
    }

    public void selectLetter(int index){
        char sellected = currentLetters.getValue().remove(index);
        currentLetters.setValue(currentLetters.getValue());
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
