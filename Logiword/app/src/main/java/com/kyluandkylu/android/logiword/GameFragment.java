package com.kyluandkylu.android.logiword;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kyluandkylu.android.logiword.ViewModel.GameViewModel;

public class GameFragment extends Fragment {

    private TextView textViewYourWord;
    private TextView textViewYourLetters;
    private TextView textViewCurrentNumber;
    private TextView textViewAlphabet;
    private ConstraintLayout constraintLayoutButtons;

    private GameViewModel gameViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewYourWord = view.findViewById(R.id.textViewYourWord);
        textViewYourWord.setText("Your word");

        textViewYourLetters = view.findViewById(R.id.textViewYourLetters);
        textViewYourLetters.setText("Your letters");

        textViewCurrentNumber = view.findViewById(R.id.textViewCurrentNumber);
        textViewAlphabet = view.findViewById(R.id.textViewAlphabet);


        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);

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
                textViewAlphabet.setMovementMethod(LinkMovementMethod.getInstance());
            }
        });

        gameViewModel.getCurrentLetters().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textViewYourLetters.setText(s);
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
    }


    private void buttonClicked(Button button) {
        String buttonText = button.getText().toString();
        if(buttonText.equals("+/-")){
            gameViewModel.negateVal();
        }else if(buttonText.equals("CE")){
            //// TODO: 07-Nov-19 implement
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
            } else {
                gameViewModel.changeCurrentValText(currentNumberSt.split(" ")[0] + " " + buttonText);
            }
        }
    }

    private SpannableString numberToSpan(int n) {
        final String alphabet = getResources().getString(R.string.alphabet);
        SpannableString span = new SpannableString(alphabet);
        if (n > 0 && n < alphabet.length()) {
            ClickableSpan clickSpan = new ClickableSpan() {
                @Override
                public void onClick(View yourTextView) {
                    //gameViewModel.addLetter(alphabet.charAt(n));

                }
            };
            span.setSpan(clickSpan, n - 1, n, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return span;
    }
}
