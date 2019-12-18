package com.kyluandkylu.android.logiword.Game;

import android.app.AlertDialog;
import android.content.Context;
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

import com.kyluandkylu.android.logiword.Authentication.AccountAuthentication;
import com.kyluandkylu.android.logiword.GlobalScore.ScoreCalculator;
import com.kyluandkylu.android.logiword.MainMenu.MainMenu;
import com.kyluandkylu.android.logiword.R;
import com.kyluandkylu.android.logiword.Retrofit.WebService;

import java.sql.Timestamp;
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

    public GameFragment() {
        yourWordText = "Your word";
    }

    public GameFragment(String chooseWord) {
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

        if (!yourWordText.equals("Your word")) {
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
                if (s.size() == 0) {
                    textViewYourLetters.setText("Your letters");
                } else {
                    String letters = "";
                    for (Character c : s) {
                        letters += c + " ";
                    }
                    letters = letters.substring(0, letters.length() - 1);
                    SpannableString spannableString = new SpannableString(letters);
                    for (int a = 0; a < s.size(); a++) {
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

                if (gameViewModel.isCurrentWordValid()) {
                    SpannableString spannableString = new SpannableString(s);
                    spannableString.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View widget) {
                            int difficulty = 1;
                            if (yourWordText.equals("Your word")) {
                                difficulty = getActivity().getPreferences(Context.MODE_PRIVATE).getInt("DIFFICULTY", 1);
                            }
                            final int scores = (int) ScoreCalculator.calculateScores(gameViewModel.getMoves(), gameViewModel.getCurrentLetters().getValue().size(), gameViewModel.getCurrentWord().getValue(), difficulty);
                            Log.d("SCORES", scores + "");
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Finish game")
                                    .setMessage("Your scores are: " + scores + "\nAre you sure you want to finish the game?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            String token = AccountAuthentication.getToken(getContext());
                                            if (token != null) {
                                                int playerId = Integer.parseInt(token);
                                                WebService webService = WebService.getInstance();
                                                String word = gameViewModel.getCurrentWord().getValue().toLowerCase();
                                                if (yourWordText.equals("Your word")) {
                                                    GameResultsModel gameResultsModel = new GameResultsModel(playerId, word, scores, gameViewModel.getStartTime(), new Timestamp(new java.util.Date().getTime()));
                                                    webService.sendGameResults(gameResultsModel);
                                                } else {
                                                    DailyChallengeAttemptModel dailyChallengeAttemptModel = new DailyChallengeAttemptModel(playerId, word, scores, 'Y');
                                                    webService.sendDailyChallengeAttempt(dailyChallengeAttemptModel);
                                                }
                                            }
                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainMenu()).commit();
                                        }
                                    }).setNegativeButton("No", null).show();
                        }
                    }, 0, spannableString.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textViewYourWord.setText(spannableString);
                } else {
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
        if (buttonText.equals("+/-")) {
            gameViewModel.negateVal();
            randomizeOperations();
        } else if (buttonText.equals("CE")) {
            gameViewModel.restartVal();
            randomizeOperations();
        } else if (buttonText.equals("R")) {
            gameViewModel.restartWord();
        } else if (buttonText.equals("<<")) {
            if (gameViewModel.isValueLongerThenOneDigit()) {
                gameViewModel.removeLeftDigit();
                randomizeOperations();
            }
        } else if (buttonText.equals(">>")) {
            if (gameViewModel.isValueLongerThenOneDigit()) {
                gameViewModel.removeRightDigit();
                randomizeOperations();
            }
        } else {
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
                if (gameViewModel.getCurrentVal().getValue() == 0) {
                    gameViewModel.changeVal(val);
                }
            } else if (val != null) {
                if (!(val == 0 && currentNumberSt.charAt(currentNumberSt.length() - 1) == '/')) {
                    gameViewModel.performOperation(currentNumberSt.charAt(currentNumberSt.length() - 1) + "", val);
                    randomizeOperations();
                } else {
                    new AlertDialog.Builder(getContext())
                            .setTitle("VERY SERIOUS VIOLATION OF THE RULES OF THE UNIVERSE")
                            .setMessage("Please don't divide by 0")
                            .setIcon(R.drawable.ic_warning_black_24dp)
                            .setNeutralButton("I promise I won't do it again", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();

                }
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

    private void randomizeOperations() {
        ArrayList<Button> numberButtons = new ArrayList<>();
        ArrayList<Button> operationButtons = new ArrayList<>();
        for (int a = 0; a < constraintLayoutButtons.getChildCount(); a++) {
            Button current = (Button) constraintLayoutButtons.getChildAt(a);
            current.setClickable(true);
            current.setTextColor(Color.rgb(0, 0, 0));
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
        int difficulty = 1;
        if (yourWordText.equals("Your word")) {
            difficulty = getActivity().getPreferences(Context.MODE_PRIVATE).getInt("DIFFICULTY", 1);
        }
        for (int a = 3 + difficulty; a > 0; a--) {
            bt = numberButtons.remove(random.nextInt(numberButtons.size()));
            bt.setClickable(false);
            bt.setTextColor(Color.rgb(150, 150, 150));
        }
        for (int a = 1 + difficulty; a > 0; a--) {
            bt = operationButtons.remove(random.nextInt(numberButtons.size()));
            bt.setClickable(false);
            bt.setTextColor(Color.rgb(150, 150, 150));
        }
    }

    private class LetterSelectionClickableSpan extends ClickableSpan {

        private int index;

        public LetterSelectionClickableSpan(int index) {
            this.index = index;
        }

        @Override
        public void onClick(@NonNull View widget) {
            gameViewModel.selectLetter(index);
        }
    }
}
