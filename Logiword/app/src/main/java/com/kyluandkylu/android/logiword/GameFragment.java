package com.kyluandkylu.android.logiword;

import android.os.Bundle;
import android.text.Html;
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

public class GameFragment extends Fragment {

    private TextView textViewYourWord;
    private TextView textViewYourLetters;
    private TextView textViewCurrentNumber;
    private TextView textViewAlphabet;
    private ConstraintLayout constraintLayoutButtons;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewYourWord = view.findViewById(R.id.textViewYourWord);
        textViewYourWord.setText("Your word");

        textViewYourLetters = view.findViewById(R.id.textViewYourLetters);
        textViewYourLetters.setText("Your letters");

        textViewCurrentNumber = view.findViewById(R.id.textViewCurrentNumber);
        textViewCurrentNumber.setText("0");

        textViewAlphabet = view.findViewById(R.id.textViewAlphabet);
        textViewAlphabet.setText(numberToSpan(2));
        textViewAlphabet.setMovementMethod(LinkMovementMethod.getInstance());



        constraintLayoutButtons = view.findViewById(R.id.constraintLayoutButtons);

        for (int a = 0; a < constraintLayoutButtons.getChildCount(); a++){
            Button button = (Button) constraintLayoutButtons.getChildAt(a);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonClicked((Button) v);
                }
            });
        }
    }


    private void buttonClicked(Button button){
        Log.d("BUTTON", button.getText().toString());
    }

    private SpannableString numberToSpan(int n){
        String alphabet = getResources().getString(R.string.alphabet);
        SpannableString span = new SpannableString(alphabet);
        if(n > 0 && n < alphabet.length()){
            ClickableSpan clickSpan = new ClickableSpan() {
                @Override
                public void onClick(View yourTextView) {
                    Log.d("TEXT CLICK" ,"TEXT CLICK");
                }
            };
            span.setSpan(clickSpan, n - 1, n, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return span;
    }
}
