package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox chhtml,chcss,chjava;
    RadioButton rd_male,rd_female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chhtml=findViewById(R.id.checkBox_html);
        chcss=findViewById(R.id.checkBox_css);
        chjava=findViewById(R.id.checkBox_java);

        rd_male=findViewById(R.id.radioButtonMale);
        rd_female=findViewById(R.id.radioButtonFemale);




    }
    boolean b=true;
    public void onoff(View view)
    {
        ImageButton btn= (ImageButton) view;

        if(b)
            btn.setImageResource(R.drawable.on);
        else
            btn.setImageResource(R.drawable.off);

        b=!b;



    }

    public void get_subjects(View view) {



        String str="";

        if(chhtml.isChecked()) str="HTML ";
        if(chcss.isChecked()) str+="CSS ";
        if(chjava.isChecked()) str+="JAVA ";

        String Gender="";
        if(rd_male.isChecked()) Gender="Male";
        if(rd_female.isChecked()) Gender="Female";



        Toast.makeText(this, "Subjects : "+str+"\nGender : "+Gender, Toast.LENGTH_SHORT).show();


    }
}
