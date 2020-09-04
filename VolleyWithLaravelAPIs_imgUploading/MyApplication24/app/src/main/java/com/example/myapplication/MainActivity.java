package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static  String url="http://192.168.0.103:90/mylaravel/public/api/";

    EditText txtName,txtPhone,txtEmail,txtPassword,txtId;

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img=findViewById(R.id.img);
        txtId=findViewById(R.id.txtId);
        txtName=findViewById(R.id.txtName);
        txtPhone=findViewById(R.id.txtPhone);
        txtEmail=findViewById(R.id.txtEmail);
        txtPassword=findViewById(R.id.txtPassword);

        try {
            Bundle bundle=getIntent().getExtras();
            String Id=bundle.getString("Id","");
            String Name=bundle.getString("Name","");
            String Phone=bundle.getString("Phone","");
            String Email=bundle.getString("Email","");
            String Password=bundle.getString("Password","");
            String img_url=bundle.getString("img_url","");
            txtId.setText(Id);
            txtName.setText(Name);
            txtPhone.setText(Phone);
            txtEmail.setText(Email);
            txtPassword.setText(Password);
            Picasso.with(this).load(img_url).into(img);
        }catch(Exception exe){}





//--------------------------------------------------------------
        txtId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                txtName.setText("");
                txtPhone.setText("");
                txtEmail.setText("");
                txtPassword.setText("");
                String id=editable.toString();



            }
        });
//--------------------------------------------------------------

    }

    public void insertStudent(View view)
    {
        Bitmap bitmap=((BitmapDrawable) img.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] imgArr=byteArrayOutputStream.toByteArray();
        final String imgBase64= Base64.encodeToString(imgArr,Base64.DEFAULT);

        final String Name=txtName.getText().toString();
        final String Phone=txtPhone.getText().toString();
        final String Email=txtEmail.getText().toString();
        String Password=txtPassword.getText().toString();


        StringRequest request=new StringRequest(
                Request.Method.POST,
                MainActivity.url+ "Students/insert",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject JObj=new JSONObject(response);
                            String res= JObj.getString("Response");
                            Toast.makeText(MainActivity.this, ""+res, Toast.LENGTH_SHORT).show();
                        }catch(Exception exxe)
                        {
                            Toast.makeText(MainActivity.this, exxe.getMessage(), Toast.LENGTH_SHORT).show();
                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String,String> params=new HashMap();
                params.put("Name",Name);
                params.put("Phone",Phone);
                params.put("Email",Email);
                params.put("Img",imgBase64);
                return params;
            }
        };


        Volley.newRequestQueue(this).add(request);


    }

    public void showInAlert(View view)
    {



    }
   String imgBase64="";
    public void updateStudent(View view)
    {
        try{
            Bitmap bitmap=((BitmapDrawable) img.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
            byte[] imgArr=byteArrayOutputStream.toByteArray();
            imgBase64= Base64.encodeToString(imgArr,Base64.DEFAULT);
        }catch(Exception ex){}


        final String id=txtId.getText().toString();
        final String Name=txtName.getText().toString();
        final String Phone=txtPhone.getText().toString();
        final String Email=txtEmail.getText().toString();
        String Password=txtPassword.getText().toString();

        StringRequest request=new StringRequest(
                Request.Method.POST,
                MainActivity.url+ "Students/update",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject JObj=new JSONObject(response);
                            String res= JObj.getString("Response");
                            Toast.makeText(MainActivity.this, ""+res, Toast.LENGTH_SHORT).show();
                        }catch(Exception exxe)
                        {
                            Toast.makeText(MainActivity.this, exxe.getMessage(), Toast.LENGTH_SHORT).show();
                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String,String> params=new HashMap();
                params.put("StudentId",id);
                params.put("Name",Name);
                params.put("Phone",Phone);
                params.put("Email",Email);
                params.put("Img",imgBase64);

                return params;
            }
        };


        Volley.newRequestQueue(this).add(request);

    }

    public void delete(View view)
    {
        String id=txtId.getText().toString();

    }

    public void Student_Activity(View view)
    {
        startActivity(new Intent(this,StudentsActivity.class));
    }

    public void select_image(View view)
    {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,101);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101 && resultCode==RESULT_OK)
        {
            Uri uri=data.getData();
            img.setImageURI(uri);

        }

    }
}
