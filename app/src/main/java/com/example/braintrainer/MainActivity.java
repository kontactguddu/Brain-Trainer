package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.braintrainer.setting.Operator;
import com.example.braintrainer.setting.Setting;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Operator operator = new Operator();

    TextView username;
    ImageView logout;


    public void goButton(View view){
        Intent i = new Intent(this, Operator.class);
        startActivity(i);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      username = findViewById(R.id.usernameText);
      logout = findViewById(R.id.newUserLogin);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            username.setText(signInAccount.getGivenName() + "\n" + signInAccount.getIdToken());

        }


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), SignIn_Activity.class);
                startActivity(intent);
            }
        });
    }

    public void settingBtn(View view) {
        Intent i = new Intent(this, Setting.class);
        startActivity(i);
    }
}