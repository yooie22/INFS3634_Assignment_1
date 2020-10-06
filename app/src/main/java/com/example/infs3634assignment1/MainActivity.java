package com.example.infs3634assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private int answer;
    public int count = 0;

    public enum RandomNumberRange {
        MAXIMUM(100), MINIMUM(1);

        private RandomNumberRange(int value){
            this.value = value;
        }

        private int value;
        public int getValue() {
            return this.value;
        }

        public void setValue(int value){
            this.value = value;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answer = createRandomNumber();
    }

    public int createRandomNumber() {
        Random randomNumber = new Random();
        return randomNumber.nextInt(MainActivity.RandomNumberRange.MAXIMUM.getValue()) + MainActivity.RandomNumberRange.MINIMUM.getValue();
    }

    private Boolean isGuessCorrect(int userGuess) {
        return userGuess == answer;
    }

    private void validateAndCheckGuess(String userInput) {
        count++;

        try {
            int guessNumber = Integer.parseInt(userInput);
            if (guessNumber <= RandomNumberRange.MINIMUM.getValue()) {
                displayInvalidUserInputToast();
            } else {
                checkNumber(guessNumber);
            }
        } catch (NumberFormatException e){
            displayInvalidUserInputToast();
        }
        if (count==5) {
            displayGameOverToast();
        }
    }

    private void checkNumber(int guessInput) {
        if (isGuessCorrect(guessInput)) {
            displayCorrectAnswerToast();
            clearUserInputEditText();
            answer = createRandomNumber();
        } else {
            displayMessageToast(guessInput > answer);
        }
    }

    private void clearUserInputEditText() {
        EditText userEditText = (EditText) findViewById(R.id.guessEditText);
        userEditText.setText("");
    }

    private void displayMessageToast(Boolean higher) {
        String message = "Try guessing again but higher";

        if (higher) {
            message = "Try guessing again but lower";
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void displayCorrectAnswerToast() {
        Toast.makeText(this, "You guessed correctly!", Toast.LENGTH_LONG).show();
    }

    private void displayInvalidUserInputToast() {
        Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
    }

    private void displayGameOverToast() {
        Toast.makeText(this, "Game Over you finished 5 attempts", Toast.LENGTH_SHORT).show();
    }

    public void onGuessSubmit(View view) {
        EditText userEditText = (EditText) findViewById(R.id.guessEditText);
        String userInput = userEditText.getText().toString();
        validateAndCheckGuess(userInput);
    }
}
