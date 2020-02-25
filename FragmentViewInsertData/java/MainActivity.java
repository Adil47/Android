package com.example.fragmentviewinsertdata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    Fragment_insert fragment_insert = new Fragment_insert(this);
    Fragment_view fragment_view = new Fragment_view(this);


    public static ArrayList<String> lst = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        frameLayout = findViewById(R.id.framelayout);

    }

    public void insertfragment(View view)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment_insert).commit();
    }

    public void viewfragment(View view)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment_view).commit();
    }
}
