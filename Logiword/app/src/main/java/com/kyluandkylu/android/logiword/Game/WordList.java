package com.kyluandkylu.android.logiword.Game;

import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class WordList {

    public static TreeSet<String> wordsTree;

    public static TreeSet<String> loadWordsFromTextFile(InputStream inputStream, ProgressBar progressBar) {
        TreeSet<String> words = new TreeSet<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String st = "";
            int rowNr = 0;
            while ((st = bufferedReader.readLine()) != null) {
                words.add(st);
                rowNr++;
                progressBar.setProgress(rowNr * 100 / 370113);
            }
        } catch (IOException e) {

        }
        return words;
    }
}
