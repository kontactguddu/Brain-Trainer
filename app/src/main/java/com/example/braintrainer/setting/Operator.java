package com.example.braintrainer.setting;

import androidx.appcompat.app.AppCompatActivity;

import com.example.braintrainer.Addition_Activity;
import com.example.braintrainer.R;
import com.example.braintrainer.Subraction_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Operator extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);
    }


    public void subtraction(View view) {
        Intent i = new Intent(this, Subraction_Activity.class);
        startActivity(i);
    }

    public void addition(View view) {
        Intent i = new Intent(this, Addition_Activity.class);
        startActivity(i);
    }
}