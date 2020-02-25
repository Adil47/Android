package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void alert_dialog(View view)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Virus found alert..");
        builder.setMessage("Are you sure do you want to kill virus? ");
        builder.setIcon(R.drawable.virus);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(MainActivity.this, "Virus Removed", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Virus Alive", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    int i=1;
    public void progress_dialog_H(View v)
    {
        final ProgressDialog progressDialog=new ProgressDialog(this);

        progressDialog.setTitle("Downloading...");
        progressDialog.setMessage("Please wait until downloading complete");
        progressDialog.setIcon(R.drawable.ic_file_download);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

    i=0;
        final Handler  handler=new Handler();

        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                progressDialog.setProgress(i++);

                if(i<=100)
                {
                    handler.postDelayed(this,500);
                }
               else
                {
                    progressDialog.hide();
                }

            }
        };
        handler.postDelayed(runnable,100);

    }
    public void progress_dialog_Ring(View view) {
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Downloading...");
        progressDialog.setMessage("Please wait until downloading complete");
        progressDialog.setIcon(R.drawable.ic_file_download);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

    }
    public void custom_alertDialog(View view)
    {
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.mylayout,null);
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(v);
        final AlertDialog alertDialog=builder.create();


        ImageButton btnClose=v.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Canceled..", Toast.LENGTH_SHORT).show();
                alertDialog.hide();
            }
        });

        ImageButton btndownload=v.findViewById(R.id.btndownload);
        btndownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.kiloo.subwaysurf"));
                startActivity(intent);
               // Toast.makeText(MainActivity.this, "Downloading..", Toast.LENGTH_SHORT).show();
                alertDialog.hide();
            }
        });

        alertDialog.show();
    }

}




















