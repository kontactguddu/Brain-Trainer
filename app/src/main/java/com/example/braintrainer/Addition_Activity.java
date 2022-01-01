package com.example.braintrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.braintrainer.setting.Level_Selection;
import com.example.braintrainer.setting.Operator;
import com.example.braintrainer.setting.Setting;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.Random;

public class Addition_Activity extends AppCompatActivity {
    
    private String TAG = "AdditionActivity Tag";
    private TextView timerTextView, questionTextView, scoreTextView, resultTextView, levelTextView;
    private Button button0, button1, button2, button3;
    private int locationOfCorrectAnswer;
    private int score = 0;
    private int totalQuestion;
    private ImageView resultEmoji;
    private Button playAgainButton, settingButton, mainMenuSelector, skipButton;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String[] correct;
    private MediaPlayer music;

    // object of Level_Selection.
    Level_Selection level_selection = new Level_Selection();

    int timeForTimer = level_selection.getTimerTime();

    String difficultyTag = level_selection.getDifficultiesTag();


    public void chooseAnswer(View view){
//        resultTextView.setVisibility(View.VISIBLE);
            resultEmoji.setVisibility(View.VISIBLE);

//        Log.v(TAG, "option tag: " + view.getTag().toString());

        if(correct[0].equals(view.getTag().toString())){
//            resultTextView.setText("Correct :>");
            resultEmoji.setBackground(getDrawable(R.drawable.success1));
            music = MediaPlayer.create(Addition_Activity.this, R.raw.successmusic);
            music.start();

            score++;
        } else {
//            resultTextView.setText("Incorrect :<");
            resultEmoji.setBackground(getDrawable(R.drawable.fali1));
            music = MediaPlayer.create(Addition_Activity.this, R.raw.failmusic);
            music.start();
        }
        totalQuestion++;
        scoreTextView.setText(Integer.toString(score)+ "/" +Integer.toString(totalQuestion));
        correct = getnewQuestion();
    }

//    public void newQuestion(){
//        correct = getnewQuestion();
//    }
    @SuppressLint("SetTextI18n")
    public void playAgain(View view){
        score = 0;
        totalQuestion = 0;
        timerTextView.setText((Integer.toString(timeForTimer)) + "s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(totalQuestion));
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        resultEmoji.setVisibility(View.INVISIBLE);
        mainMenuSelector.setVisibility(View.INVISIBLE);
        settingButton.setVisibility(View.INVISIBLE);
        playAgainButton.setText("Play Again");
        levelTextView.setText(level_selection.getDifficultiesTag());
        button0.setEnabled(true);button1.setEnabled(true);button2.setEnabled(true);button3.setEnabled(true);
        skipButton.setVisibility(View.VISIBLE);



        correct = getnewQuestion();
        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) +"s");
            }

            @Override
            public void onFinish() {
                resultEmoji.setVisibility(View.INVISIBLE);
                resultTextView.setVisibility(View.VISIBLE);
                resultTextView.setText("Time up!");
                music = MediaPlayer.create(Addition_Activity.this, R.raw.gameovermusic);
                music.start();

                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setVisibility(View.VISIBLE);
//                settingButton.setVisibility(View.VISIBLE);
//                mainMenuSelector.setVisibility(View.VISIBLE);
                skipButton.setVisibility(View.INVISIBLE);

                button0.setEnabled(false);button1.setEnabled(false);button2.setEnabled(false);button3.setEnabled(false);

            }
        }.start();


    }

    public void mainMenuBtn(View view){
        Intent intent = new Intent(this, Operator.class);
        startActivity(intent);
    }
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_);

        timerTextView = findViewById(R.id.timerTextView);
        questionTextView = findViewById(R.id.questionTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        levelTextView = findViewById(R.id.levelTextView);
        settingButton = findViewById(R.id.settingButton);
        mainMenuSelector = findViewById(R.id.mainMenuBtn);
        skipButton = findViewById(R.id.skipButton);
        resultEmoji = findViewById(R.id.resultEmoji);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Addition").child("Level1");

        correct = getnewQuestion();
    }

    public void settingBtn(View view) {
        Intent i = new Intent(this, Setting.class);
        startActivity(i);
    }

    private String[] getnewQuestion(){

        // calling add value event listener method
        // for getting the values from database.
        final String[] correctOption = new String[1];
        Random random = new Random();
        int quesNum = random.nextInt(80) + 1;

//        Log.v(TAG, "question number : " + Integer.toString(quesNum));
//        Query questionQuery = myRef.child(Integer.toString(quesNum));
        Query questionQuery;
        questionQuery = myRef.child(Integer.toString(quesNum));

        questionQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ques = dataSnapshot.child("questions").getValue().toString();
                String option1 = dataSnapshot.child("option_0").getValue().toString();
                String option2 = dataSnapshot.child("option_1").getValue().toString();
                String option3 = dataSnapshot.child("option_2").getValue().toString();
                String option4 = dataSnapshot.child("option_3").getValue().toString();
                String correct_option = dataSnapshot.child("correct_option").getValue().toString();

                Log.d("TAG", ques + " " + option1 + " " + correct_option);
                questionTextView.setText(ques);
                button0.setText(option1);
                button1.setText(option2);
                button2.setText(option3);
                button3.setText(option4);

                correctOption[0] = correct_option;

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled", databaseError.toException());
            }
        });

        return correctOption;

    }

    public void skipBtn(View view) {
        totalQuestion++;
        scoreTextView.setText(Integer.toString(score)+ "/" +Integer.toString(totalQuestion));
        correct = getnewQuestion();
        music = MediaPlayer.create(Addition_Activity.this, R.raw.skipmusic);
        music.start();

    }
}