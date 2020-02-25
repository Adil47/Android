package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {


    ViewFlipper viewFlipper;
    RadioButton radio_q1,radio_q2,radio_q3,radio_q4,radio_q5;
    CheckedTextView txtResult_q1,txtResult_q2,txtResult_q3,txtResult_q4,txtResult_q5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper=findViewById(R.id.viewflipper);
        viewFlipper.setFlipInterval(2000);


        radio_q1=findViewById(R.id.rb_q1);
        radio_q2=findViewById(R.id.rb_q2);
        radio_q3=findViewById(R.id.rb_q3);
        radio_q4=findViewById(R.id.rb_q4);
        radio_q5=findViewById(R.id.rb_q5);

        txtResult_q1=findViewById(R.id.Result_q1);
        txtResult_q2=findViewById(R.id.Result_q2);
        txtResult_q3=findViewById(R.id.Result_q3);
        txtResult_q4=findViewById(R.id.Result_q4);
        txtResult_q5=findViewById(R.id.Result_q5);



    }

    public void previous(View view) {
        viewFlipper.showPrevious();
        setResult();
    }

    public void play(View view) {
        viewFlipper.startFlipping();
    }

    public void stop(View view) {
        viewFlipper.stopFlipping();
    }


    public void next(View view) {
        viewFlipper.showNext();

      setResult();

      if(viewFlipper.getCurrentView().getId()==R.id.resultLayout)
      {
          //Toast.makeText(this, "resultLayout", Toast.LENGTH_SHORT).show();

          LinearLayout linearLayout=findViewById(R.id.btnLayout);
          linearLayout.setVisibility(View.INVISIBLE);


      }

    }

    private void setResult() {
        if(radio_q1.isChecked()) txtResult_q1.setCheckMarkDrawable(R.drawable.ic_check);
        else txtResult_q1.setCheckMarkDrawable(R.drawable.ic_close);

        if(radio_q2.isChecked()) txtResult_q2.setCheckMarkDrawable(R.drawable.ic_check);
        else txtResult_q2.setCheckMarkDrawable(R.drawable.ic_close);

        if(radio_q3.isChecked()) txtResult_q3.setCheckMarkDrawable(R.drawable.ic_check);
        else txtResult_q3.setCheckMarkDrawable(R.drawable.ic_close);

        if(radio_q4.isChecked()) txtResult_q4.setCheckMarkDrawable(R.drawable.ic_check);
        else txtResult_q4.setCheckMarkDrawable(R.drawable.ic_close);

        if(radio_q5.isChecked()) txtResult_q5.setCheckMarkDrawable(R.drawable.ic_check);
        else txtResult_q5.setCheckMarkDrawable(R.drawable.ic_close);
    }
}
