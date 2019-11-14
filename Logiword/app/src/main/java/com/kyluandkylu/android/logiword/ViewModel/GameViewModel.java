package com.kyluandkylu.android.logiword.ViewModel;

import android.app.Application;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class GameViewModel extends AndroidViewModel {

    private MutableLiveData<Integer> currentVal;
    private MutableLiveData<String> currentValText;
    private MutableLiveData<ArrayList<Character>> currentLetters;
    private MutableLiveData<String> currentWord;
    private ArrayList<Move> moves;

    private TreeSet<String> words;

    public GameViewModel(@NonNull Application application){
        super(application);
        currentVal = new MutableLiveData<>();
        currentVal.setValue(0);
        currentValText = new MutableLiveData<>();
        currentValText.setValue("0");
        currentLetters = new MutableLiveData<>();
        currentLetters.setValue(new ArrayList<Character>());
        currentWord = new MutableLiveData<>();
        currentWord.setValue("Your word");
        try {
            words = WordList.getWords(application.getAssets().open("words_alpha.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        moves = new ArrayList<>();
    }


    public MutableLiveData<String> getCurrentWord() {
        return currentWord;
    }

    public boolean isCurrentWordValid(){
        return words.contains(currentWord.getValue().toLowerCase()) && currentWord.getValue().length() >= 3;
    }

    private void addLetterToWord(char letter){
        if(currentWord.getValue().equals("Your word")){
            currentWord.setValue("");
        }
        currentWord.setValue(currentWord.getValue() + letter);
    }

    public MutableLiveData<ArrayList<Character>> getCurrentLetters() {
        return currentLetters;
    }

    public void addLetter(char letter){
        currentLetters.getValue().add(letter);
        currentLetters.setValue(currentLetters.getValue());
    }

    public void selectLetter(int index){
        char selected = currentLetters.getValue().remove(index);
        addLetterToWord(selected);
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
