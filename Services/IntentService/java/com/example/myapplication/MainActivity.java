package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
    }

    public void start_serivice(View view) {

        String Name=editText.getText().toString();

        Intent intent=new Intent(this,MyIntentService.class);
        intent.putExtra("Name",Name);
        startService(intent);

    }
}
