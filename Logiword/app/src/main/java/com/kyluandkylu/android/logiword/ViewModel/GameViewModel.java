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
    private String yourWordText;

    public GameViewModel(@NonNull Application application){
        super(application);
        currentVal = new MutableLiveData<>();
        currentVal.setValue(0);
        currentValText = new MutableLiveData<>();
        currentValText.setValue("0");
        currentLetters = new MutableLiveData<>();
        currentLetters.setValue(new ArrayList<Character>());
        currentWord = new MutableLiveData<>();
        currentWord.setValue(yourWordText);
        words = WordList.getWords();
        moves = new ArrayList<>();
    }

    public void removeLeftDigit(){
        int current = currentVal.getValue();
        if(current >= 10){
            String temp = Integer.toString(current);
            current = Integer.parseInt(temp.substring(0, temp.length() - 1));
            moves.add(new Calculation("<<",currentVal.getValue(), current));
            currentVal.setValue(current);
        }
    }

    public void removeRightDigit(){
        int current = currentVal.getValue();
        if(current >= 10){
            String temp = Integer.toString(current);
            current = Integer.parseInt(temp.substring(1));
            moves.add(new Calculation(">>",currentVal.getValue(), current));
            currentVal.setValue(current);
        }
    }

    public MutableLiveData<String> getCurrentWord() {
        return currentWord;
    }

    public boolean isCurrentWordValid(){
        if(yourWordText.equals("Your word")){
            return words.contains(currentWord.getValue().toLowerCase()) && currentWord.getValue().length() >= 3;
        }else{
            return currentWord.getValue().toLowerCase().equals(yourWordText.toLowerCase()) && moves.size() > 0;
        }

    }

    private void addLetterToWord(char letter){
        if(currentWord.getValue().equals(yourWordText)){
            currentWord.setValue("");
        }
        currentWord.setValue(currentWord.getValue() + letter);
    }

    public void restartWord(){
        if(!currentWord.getValue().toLowerCase().equals(yourWordText.toLowerCase())){
            String old = currentWord.getValue();
            for(char c : old.toCharArray()){
                currentLetters.getValue().add(c);
            }
            currentLetters.setValue(currentLetters.getValue());
            currentWord.setValue(yourWordText);
        }
    }

    public MutableLiveData<ArrayList<Character>> getCurrentLetters() {
        return currentLetters;
    }

    public void addLetter(char letter){
        currentLetters.getValue().add(letter);
        moves.add(new LetterSetChange(letter));
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

    public void restartVal(){
        moves.add(new Calculation("CE", currentVal.getValue(), 0));
        currentVal.setValue(0);
    }

    public void negateVal(){
        moves.add(new Calculation("+/-", currentVal.getValue(), currentVal.getValue() * -1));
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
                // TODO: 20-Nov-19 Remove 0
                if(val != 0){
                    currentVal.setValue(v / val);
                }
                break;
            case "^":
                currentVal.setValue((int) (Math.pow(v, val)));
                break;
        }
        moves.add(new Calculation(type, v, val));
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setYourWordText(String yourWordText) {
        this.yourWordText = yourWordText;
        currentWord.setValue(yourWordText);
    }
}
