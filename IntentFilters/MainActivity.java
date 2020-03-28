package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void google(View view)
    {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.google.com"));
        Intent chooser=Intent.createChooser(intent,"select browser");
        startActivity(chooser);


    }

    public void Email(View view)
    {


        Intent intent =new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("message/rfc822");

        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"Name.ak47@gmail.com","muhammadabubakar45@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT,"Addmission Form");
        intent.putExtra(Intent.EXTRA_TEXT,"My Data sdjs s kdskldsldj slkdj slkdj slkdj");

        Intent chooser=Intent.createChooser(intent,"select one");
        startActivity(chooser);


    }

    public void Map(View view)
    {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:24.904215,67.192321"));
        Intent chooser=Intent.createChooser(intent,"select any map");
        startActivity(chooser);
    }
}
