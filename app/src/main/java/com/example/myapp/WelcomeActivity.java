package com.example.myapp;

import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        FragmentManager fragmentManager = getSupportFragmentManager();
        LogInFragment logInFragment = new LogInFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.myFragmentContainerView,logInFragment,"logInFragment");
        transaction.commit();
       // CountDownTimer countDownTimer = new CountDownTimer(4000,400) {
           // @Override
           // public void onTick(long millisUntilFinished) {
           // }

           // @Override
           // public void onFinish() {

           // }
        //};
        //countDownTimer.start();


    }
    public void changeFragmentToSignUp(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        SignUpFragment signUpFragment = new SignUpFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.myFragmentContainerView,signUpFragment,"signUpFragment");
        transaction.commit();

    }
    public void changeFragmentToLogIn(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        LogInFragment logInFragment = new LogInFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.myFragmentContainerView,logInFragment,"logInFragment");
        transaction.commit();

    }

}