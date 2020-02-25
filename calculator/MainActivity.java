package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtinput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        txtinput=findViewById(R.id.txtinput);



    }

    String Operator="";
    boolean isOptSet=false;
    float result=0;

    public void numberTyped(View view) {

        Button btn=(Button) view;
        String num=btn.getText().toString();

        if(isOptSet)
        {

            txtinput.setText("");
            isOptSet=false;
        }

        String txt=txtinput.getText().toString();

        if(txt.contains(".") && num.equals("."))
        {

        }
        else if (txt.equals("") && num.equals("."))
        {
            txtinput.setText("0.");
        }

        else
        {
            txtinput.setText(txt+num);
        }





    }

    public void Cancel(View view) {
        txtinput.setText("");
    }

    public void ClearEntry(View view) {

        String txt=txtinput.getText().toString();

        try
        {
            txtinput.setText(txt.substring(0,txt.length()-1));
        }catch (Exception ex){}




    }

    public void OperatorTyped(View view)
    {

        if(Operator!="")
        {
            equalTo(view);
        }


        Operator=((Button)view).getText().toString();

        isOptSet=true;


        String txt=txtinput.getText().toString();
        if(!txt.equals(""))
        {
            result=Float.parseFloat(txt);
        }

    }

    public void equalTo(View view)
    {
        String txt=txtinput.getText().toString();
        float val2=Float.parseFloat(txt);
        switch (Operator)
        {
            case "+":
                result=result+val2;
                txtinput.setText(result+"");
                break;
            case "-":
                result=result-val2;
                txtinput.setText(result+"");
                break;
            case "*":
                result=result*val2;
                txtinput.setText(result+"");
                break;
            case "/":
                result=result/val2;
                txtinput.setText(result+"");
                break;

        }
    }
}
