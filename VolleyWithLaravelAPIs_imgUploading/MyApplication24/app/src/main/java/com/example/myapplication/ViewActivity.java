package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.awt.font.TextAttribute;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewActivity extends AppCompatActivity {

    TextView txtId,txtName,txtPhone,txtEmail,txtPass;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        getSupportActionBar().hide();
        circleImageView=findViewById(R.id.s_img);

        txtId=findViewById(R.id.s_id);
        txtName=findViewById(R.id.s_name);
        txtPhone=findViewById(R.id.s_phone);
        txtEmail=findViewById(R.id.s_email);
        txtPass=findViewById(R.id.s_pass);




        Bundle bundle=getIntent().getExtras();
        String id=bundle.getString("Id");
        String name=bundle.getString("Name");
        String phone=bundle.getString("Phone");
        String email=bundle.getString("Email");
        String pass=bundle.getString("Password");
        String url=bundle.getString("Img");


        txtId.setText("Id : "+id);
        txtName.setText("Name : "+name);
        txtPhone.setText("Phone : "+phone);
        txtEmail.setText("Email : "+email);
        txtPass.setText("Password : "+pass);

        Picasso.with(this).load(url).into(circleImageView);


    }
}
