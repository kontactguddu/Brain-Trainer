package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.braintrainer.setting.Level_Selection;
import com.example.braintrainer.setting.Operator;
import com.example.braintrainer.setting.Setting;

import java.util.ArrayList;
import java.util.Random;

public class Subraction_Activity extends AppCompatActivity {

    TextView timerTextView, sumTextView, scoreTextView, resultTextView, levelTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button button0, button1, button2, button3;
    int locationOfCorrectAnswer;
    int score = 0;
    int totalQuestion;
    Button playAgainButton, levelSelector, mainMenuSelector;

    public void chooseAnswer(View view){
        resultTextView.setVisibility(View.VISIBLE);
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct :)");
            score++;
        } else {
            resultTextView.setText("Incorrect :(");
        }
        totalQuestion++;
        scoreTextView.setText(Integer.toString(score)+ "/" +Integer.toString(totalQuestion));
        newQuestion();
    }

    public void newQuestion(){
        Random rand = new Random();             // rand object created from random class
        int a = rand.nextInt(21);       // select integer between 0 to 20 total 21 numbers
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) +" - " + Integer.toString(b));      // set new question
        locationOfCorrectAnswer = rand.nextInt(4);              // location of answer is varying

        answers.clear();            // to delete all previous saved value in array

        for(int i=0; i<4; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(a-b);
            }else {
                int wrongAnswer = rand.nextInt(21);
                while(wrongAnswer == a-b){
                    wrongAnswer = rand.nextInt(21);
                    for(int j=0; j<i; j++){
                        if(wrongAnswer == answers.get(j)){
                            wrongAnswer = rand.nextInt(21);
                        }
                    }
                }

                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(View view){
        score = 0;
        totalQuestion = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(totalQuestion));
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        mainMenuSelector.setVisibility(View.INVISIBLE);
        levelSelector.setVisibility(View.INVISIBLE);
        playAgainButton.setText("Play Again");
        levelTextView.setText("Warmup");
        button0.setEnabled(true);button1.setEnabled(true);button2.setEnabled(true);button3.setEnabled(true);


        newQuestion();
        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) +"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Time up!");
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setVisibility(View.VISIBLE);
                levelSelector.setVisibility(View.VISIBLE);
                mainMenuSelector.setVisibility(View.VISIBLE);

                button0.setEnabled(false);button1.setEnabled(false);button2.setEnabled(false);button3.setEnabled(false);

            }
        }.start();


    }

    public void mainMenuBtn(View view){
        Intent intent = new Intent(this, Operator.class);
        startActivity(intent);
    }
    public void settingBtn(View view){
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subraction_);

        timerTextView = findViewById(R.id.timerTextView);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        levelTextView = findViewById(R.id.levelTextView);

        levelSelector = findViewById(R.id.settingButton);
        mainMenuSelector = findViewById(R.id.mainMenuBtn);
    }
}