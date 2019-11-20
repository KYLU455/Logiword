package com.kyluandkylu.android.logiword.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kyluandkylu.android.logiword.R;
import com.kyluandkylu.android.logiword.Score.ScoreCalculator;
import com.kyluandkylu.android.logiword.ViewModel.GameViewModel;

import java.util.ArrayList;
import java.util.Random;

public class GameFragment extends Fragment {

    private TextView textViewYourWord;
    private TextView textViewYourLetters;
    private TextView textViewCurrentNumber;
    private TextView textViewAlphabet;
    private ConstraintLayout constraintLayoutButtons;

    private GameViewModel gameViewModel;

    private String yourWordText;

    public GameFragment(){
        yourWordText = "Your word";
    }

    public GameFragment(String chooseWord){
        yourWordText = chooseWord;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(!yourWordText.equals("Your word")){
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(yourWordText);
        }

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        gameViewModel.setYourWordText(yourWordText);

        textViewYourWord = view.findViewById(R.id.textViewYourWord);
        textViewYourWord.setText(yourWordText);
        textViewYourWord.setMovementMethod(LinkMovementMethod.getInstance());

        textViewYourLetters = view.findViewById(R.id.textViewYourLetters);
        textViewYourLetters.setText("Your letters");
        textViewYourLetters.setMovementMethod(LinkMovementMethod.getInstance());

        textViewCurrentNumber = view.findViewById(R.id.textViewCurrentNumber);
        textViewAlphabet = view.findViewById(R.id.textViewAlphabet);
        textViewAlphabet.setMovementMethod(LinkMovementMethod.getInstance());

        gameViewModel.getCurrentValText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textViewCurrentNumber.setText(s);
            }
        });


        gameViewModel.getCurrentVal().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                gameViewModel.changeCurrentValText(integer.toString());
                textViewAlphabet.setText(numberToSpan(integer));

            }
        });

        gameViewModel.getCurrentLetters().observe(this, new Observer<ArrayList<Character>>() {
            @Override
            public void onChanged(ArrayList<Character> s) {
                if(s.size() == 0){
                    textViewYourLetters.setText("Your letters");
                }else {
                    String letters = "";
                    for(Character c : s){
                        letters += c + " ";
                    }
                    letters = letters.substring(0, letters.length() - 1);
                    SpannableString spannableString = new SpannableString(letters);
                    for(int a = 0 ; a < s.size(); a++){
                        spannableString.setSpan(new LetterSelectionClickableSpan(a), a * 2, a * 2 + 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    textViewYourLetters.setText(spannableString);
                }
                gameViewModel.changeVal(0);
            }
        });

        gameViewModel.getCurrentWord().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                if(gameViewModel.isCurrentWordValid()){
                    SpannableString spannableString = new SpannableString(s);
                    spannableString.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View widget) {
                            Log.d("SCORES", ScoreCalculator.calculateScores(gameViewModel.getMoves(),gameViewModel.getCurrentLetters().getValue().size(),gameViewModel.getCurrentWord().getValue()) + "");
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Finish game")
                                    .setMessage("Are you sure you want to finish the game?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // TODO: 14-Nov-19 IMPLEMENT ON WORD SUBMIT and add used letters
                                        }
                                    }).setNegativeButton("No", null).show();
                        }
                    },0, spannableString.length(),SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textViewYourWord.setText(spannableString);
                }else {
                    textViewYourWord.setText(s);
                }
            }
        });

        constraintLayoutButtons = view.findViewById(R.id.constraintLayoutButtons);

        for (int a = 0; a < constraintLayoutButtons.getChildCount(); a++) {
            Button button = (Button) constraintLayoutButtons.getChildAt(a);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonClicked((Button) v);
                }
            });
        }

        randomizeOperations();
    }


    private void buttonClicked(Button button) {
        String buttonText = button.getText().toString();
        if(buttonText.equals("+/-")){
            gameViewModel.negateVal();
            randomizeOperations();
        }else if(buttonText.equals("CE")){
            gameViewModel.restartVal();
            randomizeOperations();
        }else if(buttonText.equals("R")){
            gameViewModel.restartWord();
        }else if(buttonText.equals("<<")){
            gameViewModel.removeLeftDigit();
            randomizeOperations();
        }
        else if(buttonText.equals(">>")){
            gameViewModel.removeRightDigit();
            randomizeOperations();
        }
        else {
            String currentNumberSt = gameViewModel.getCurrentValText().getValue();
            Integer val = null;
            try {
                val = Integer.parseInt(buttonText);
            } catch (NumberFormatException e) {

            }
            if (currentNumberSt.charAt(currentNumberSt.length() - 1) >= '0'
                    && currentNumberSt.charAt(currentNumberSt.length() - 1) <= '9'
                    && val == null) {
                gameViewModel.changeCurrentValText(currentNumberSt + " " + buttonText);
            } else if (currentNumberSt.charAt(currentNumberSt.length() - 1) >= '0'
                    && currentNumberSt.charAt(currentNumberSt.length() - 1) <= '9'
                    && val != null) {
                if(gameViewModel.getCurrentVal().getValue() == 0){
                    gameViewModel.changeVal(val);
                }
            } else if (val != null) {
                gameViewModel.performOperation(currentNumberSt.charAt(currentNumberSt.length() - 1) + "", val);
                randomizeOperations();
            } else {
                gameViewModel.changeCurrentValText(currentNumberSt.split(" ")[0] + " " + buttonText);
            }
        }
    }

    private SpannableString numberToSpan(int n) {
        final String alphabet = getResources().getString(R.string.alphabet);
        final int finalN = n;
        SpannableString span = new SpannableString(alphabet);
        if (n > 0 && n < alphabet.length()) {
            ClickableSpan clickSpan = new ClickableSpan() {
                @Override
                public void onClick(View yourTextView) {
                    gameViewModel.addLetter(alphabet.charAt(finalN - 1));
                    randomizeOperations();
                }
            };
            span.setSpan(clickSpan, n - 1, n, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return span;
    }


    private class LetterSelectionClickableSpan extends ClickableSpan{

        private int index;

        public LetterSelectionClickableSpan(int index){
            this.index = index;
        }

        @Override
        public void onClick(@NonNull View widget) {
            gameViewModel.selectLetter(index);
        }
    }

    private void randomizeOperations() {
        ArrayList<Button> numberButtons = new ArrayList<>();
        ArrayList<Button> operationButtons = new ArrayList<>();
        for (int a = 0; a < constraintLayoutButtons.getChildCount(); a++) {
            Button current = (Button) constraintLayoutButtons.getChildAt(a);
            current.setClickable(true);
            current.setTextColor(Color.rgb(0,0,0));
            switch (current.getText().toString()) {
                case "+":
                case "-":
                case "*":
                case "^":
                case "/":
                case "+/-":
                case "<<":
                case ">>":
                    operationButtons.add(current);
                    break;
                case "R":
                case "CE":
                    break;
                default:
                    numberButtons.add(current);
            }
        }
        Random random = new Random();
        Button bt;
        for(int a = 0; a < 4; a++){
            bt =  numberButtons.remove(random.nextInt(numberButtons.size()));
            bt.setClickable(false);
            bt.setTextColor(Color.rgb(150,150,150));
        }
        for(int a = 0; a < 2; a++){
            bt =  operationButtons.remove(random.nextInt(numberButtons.size()));
            bt.setClickable(false);
            bt.setTextColor(Color.rgb(150,150,150));
        }
    }
}
