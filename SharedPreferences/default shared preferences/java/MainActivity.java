package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void settings(View view)
    {

        startActivity(new Intent(this,SettingsActivity.class));
    }

    public void getpref(View view) {

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        String str="";

        String Email=sharedPreferences.getString("email","not found");
        String Gender=sharedPreferences.getString("gender","not found");
        String signature=sharedPreferences.getString("signature","not found");

        str+="Email : "+Email+"\n";
        str+="Gender : "+Gender+"\n";
        str+="Signature : "+signature+"\n";



        Set<String> subjects=   sharedPreferences.getStringSet("subjects",null);
for(String s:subjects)
{
    str+="-----"+s+"\n";
}


        Toast.makeText(this, str, Toast.LENGTH_LONG).show();

    }
}
