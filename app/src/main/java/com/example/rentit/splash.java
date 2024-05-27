package com.example.rentit;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class splash extends AppCompatActivity {
    LottieAnimationView logo;
    TextView name;
    int duration =2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo=findViewById(R.id.icon);
        name=findViewById(R.id.name);

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.translate_animation);
        name.setAnimation(animation);

       new Handler().postDelayed(() -> {
           Intent intent=new Intent(getApplicationContext(), MainActivity.class);
           startActivity(intent);
           finish();
       },duration);
    }
}