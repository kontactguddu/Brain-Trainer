package com.example.braintrainer.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.braintrainer.MainActivity;
import com.example.braintrainer.R;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void stgLevel(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }



}