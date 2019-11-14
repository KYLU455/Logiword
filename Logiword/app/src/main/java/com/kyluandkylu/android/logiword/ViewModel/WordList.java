package com.kyluandkylu.android.logiword.ViewModel;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class WordList {

    private static WordList wordList;
    private static TreeSet<String> wordsTree;

    private WordList(TreeSet<String> wordsTree) {
        this.wordsTree = wordsTree;
    }

    public static TreeSet<String> getWords(InputStream inputStream){
        TreeSet<String> words = new TreeSet<>();
        if(wordList == null && inputStream != null){
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String st = "";
                while ((st = bufferedReader.readLine()) != null){
                    words.add(st);
                }
            }catch (IOException e){

            }
            wordList = new WordList(words);
        }
        return wordsTree;
    }
}
