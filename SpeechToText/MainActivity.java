package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    EditText txtName,txtPhone,txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.textView);
        txtPhone=findViewById(R.id.txtPhone);
        txtEmail=findViewById(R.id.txtEmail);
        txtName=findViewById(R.id.txtName);
    }


    public void speechView(View view) {

        spech(101);

    }
    String textFor="";
    public  void spech(int code)
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");
        try{
            startActivityForResult(intent,code);
        }
        catch (Exception ex){
            tv.setText(ex.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101 && resultCode==RESULT_OK)
        {
            ArrayList result = data .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String str=result.get(0).toString();
            tv.setText(str);
            if(str.toLowerCase().contains("email"))
            {
                txtEmail.requestFocus();
                textFor="Email";
                spech(102);

            }
            if(str.toLowerCase().contains("phone"))
            {
                txtPhone.requestFocus();
                textFor="Phone";
                spech(102);
            }
            if(str.toLowerCase().contains("name"))
            {
                txtName.requestFocus();
                textFor="Name";
                spech(102);
            }
        }
        if(requestCode==102 && resultCode==RESULT_OK)
        {
            ArrayList result = data .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String str=result.get(0).toString();
            switch (textFor)
            {
                case "Email":
                    txtEmail.setText(str);
                    break;

                case "Phone":
                    txtPhone.setText(str);
                    break;

                case "Name":
                    txtName.setText(str);
                    break;


            }


        }


    }
}
