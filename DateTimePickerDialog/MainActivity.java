package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText txtDate,txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDate=findViewById(R.id.txtDate);
        txtTime=findViewById(R.id.txtTime);
    }
    public void pickDate(View view)
    {
        String day          = (String) DateFormat.format("dd",   new Date()); // 20
        String monthString  = (String) DateFormat.format("MMM",  new Date()); // Jun
        String monthNumber  = (String) DateFormat.format("MM",   new Date()); // 06
        String year         = (String) DateFormat.format("yyyy", new Date());

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                txtDate.setText(dayOfMonth+"-"+(month+1)+"-"+year);

            }
        },Integer.parseInt(year),Integer.parseInt(monthNumber)-1,Integer.parseInt(day));
        datePickerDialog.show();
    }

    public void pickTime(View view) {
        String H         = (String) DateFormat.format("HH", new Date());
        String M         = (String) DateFormat.format("mm", new Date());

        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                txtTime.setText(hourOfDay+":"+minute);
            }
        }, Integer.parseInt(H), Integer.parseInt(M), false);
        timePickerDialog.show();
    }
}
