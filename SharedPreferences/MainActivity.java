package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtEmail,txtPassword;
    CheckBox checkBox;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail=findViewById(R.id.txtEmail);
        txtPassword=findViewById(R.id.txtPassword);
        checkBox=findViewById(R.id.ch_remember_me);



        // Shared preferences , default shared preferences


         preferences=getSharedPreferences("Login",MODE_PRIVATE);

        String email=preferences.getString("Email","");
        String pass=preferences.getString("Password","");

        txtEmail.setText(email);
        txtPassword.setText(pass);

        if(email!="")
        {
            startActivity(new Intent(this,Main2Activity.class));
            this.finish();
        }
        





    }

    public void login(View view)
    {
        if(checkBox.isChecked())
        {
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("Email",txtEmail.getText().toString());
            editor.putString("Password",txtPassword.getText().toString());
            editor.commit();
        }

        else
        {
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("Email","");
            editor.putString("Password","");
            editor.commit();
        }


        startActivity(new Intent(this,Main2Activity.class));

        this.finish();

    }
}
