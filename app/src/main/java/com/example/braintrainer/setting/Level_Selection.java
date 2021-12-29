package com.example.braintrainer.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.braintrainer.R;

public class Level_Selection extends AppCompatActivity {



    private int levelCode = 200;
    private int timerTime = 30;
    private String difficultiesTag = "Warmup";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level__selection);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.warmupRadio:      // levelcode = 200
                if (checked)
                    levelCode = 200;
                    timerTime = 20;
                    difficultiesTag = "Warmup";
                    break;
            case R.id.easyRadio:
                if (checked)
                    levelCode = 201;
                    setDifficultiesTag("Easy");
                    setTimerTime(30);
                    break;
            case R.id.mediumRadio:
                if (checked)
                    levelCode = 202;
                    setDifficultiesTag("Medium");
                    setTimerTime(40);
                    break;
            case R.id.hardRadio:
                if (checked)
                    levelCode = 203;
                    setDifficultiesTag("Hard");
                    setTimerTime(50);
                    break;
            case R.id.toughRadio:
                if (checked)
                    levelCode = 204;
                    setDifficultiesTag("Tough");
                    setTimerTime(60);
                    break;
            default:  levelCode = 201;
                      setDifficultiesTag("Easy");
                      setTimerTime(30);
                      break;
        }
    }

    public String getDifficultiesTag() {
        return difficultiesTag;
    }

    public void setDifficultiesTag(String difficultiesTag) {
        this.difficultiesTag = difficultiesTag;
    }

    public int getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(int levelCode) {
        this.levelCode = levelCode;
    }

    public int getTimerTime() {
        return timerTime;
    }

    public void setTimerTime(int timerTime) {
        this.timerTime = timerTime;
    }
}