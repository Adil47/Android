package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class AnimationActivity extends AppCompatActivity {

    Button btn,btn1;
    Animation rotate_anim,blink_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        rotate_anim= AnimationUtils.loadAnimation(this,R.anim.myanim_rotaion);
        blink_anim= AnimationUtils.loadAnimation(this,R.anim.blink);


        btn=findViewById(R.id.btn);
        btn1=findViewById(R.id.btn1);


        btn.startAnimation(rotate_anim);
        btn1.startAnimation(blink_anim);
    }

boolean IsPlaying=true;
    public void animstate(View view) {

    if(IsPlaying)
    {
        btn.clearAnimation();
    }
    else
    {
        btn.startAnimation(rotate_anim);
    }
    IsPlaying=!IsPlaying;

    }
}
