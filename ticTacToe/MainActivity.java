package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);
        b3=findViewById(R.id.btn3);
        b4=findViewById(R.id.btn4);
        b5=findViewById(R.id.btn5);
        b6=findViewById(R.id.btn6);
        b7=findViewById(R.id.btn7);
        b8=findViewById(R.id.btn8);
        b9=findViewById(R.id.btn9);



    }
    boolean b=true;
    int counter=0;
    public void tick(View view)
    {
        Button btn=(Button) view;
        if(b)
        {
            btn.setText("X");
            btn.setBackgroundColor(Color.RED);

        }
        else
        {
            btn.setText("O");
            btn.setBackgroundColor(Color.GREEN);
        }
        btn.setOnClickListener(null);
        b=!b;
        counter++;
        // check horizontally
        if(iff(b1,b2,b3) || iff(b4,b5,b6) || iff(b7,b8,b9))
        {
           alert();
        }
        // check Vertically
        else if(iff(b1,b4,b7) || iff(b2,b5,b8) || iff(b3,b6,b9))
        {
            alert();
        }
        // check diagonally
        else if(iff(b1,b5,b9) || iff(b3,b5,b7))
        {
            alert();
        }
        else
        {
            if(counter==9)
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Oopps....");
                builder.setMessage("Match Draw");
                builder.create().show();
                reset();
            }

        }
    }

    private boolean iff(Button btn1, Button btn2, Button btn3)
    {
       return btn1.getText().toString().equals(btn2.getText().toString()) && btn2.getText().toString().equals(btn3.getText().toString()) && btn1.getText().toString()!="";
    }

    private void alert()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Congratulations...");
        ImageView img=new  ImageView(this);
        img.setImageResource(R.drawable.congratulations);
        builder.setView(img);
        String winner="X";
        if(b) winner="O";
        builder.setMessage(winner+" won the match");
        builder.create().show();
        reset();
    }

    private void reset()
    {
        b=true;
        counter=0;
        Button [] btns={b1,b2,b3,b4,b5,b6,b7,b8,b9};
        for(Button btn:btns)
        {
            btn.setText("");
            btn.setBackgroundColor(Color.WHITE);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tick(v);
                }
            });
        }
    }


}
