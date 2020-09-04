package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText txtName,txtPhone,txtEmail,txttitle,txtmessage;
    String token="";
    public static String ip="122.8.184.207";
    Spinner spinner;
    ArrayAdapter<String> adapter;
    ArrayList<String> lst=new ArrayList<>();
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName=findViewById(R.id.txtName);
        txtPhone=findViewById(R.id.txtPhone);
        txtEmail=findViewById(R.id.txtEmail);

        txttitle=findViewById(R.id.txtTitle);
        txtmessage=findViewById(R.id.txtMessage);

        requestQueue=Volley.newRequestQueue(this);

        spinner=findViewById(R.id.spinner);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lst);
        spinner.setAdapter(adapter);
        fetchEmail(new View(this));
        // --------------------------------------------
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Token Error", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                         token = task.getResult().getToken();
Log.d("Token---",token);
                        // Log and toast
                        Toast.makeText(MainActivity.this, ""+token, Toast.LENGTH_SHORT).show();
                    }
                });

        //------------------------------------------------

        BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle=intent.getExtras();
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(bundle.getString("title"))
                        .setMessage(bundle.getString("message"))
                        .create()
                        .show();

            }
        };
        registerReceiver(broadcastReceiver,new IntentFilter("notification_broadcast"));


        //----------------------------------------------------

    }


    public void register(View view)
    {

        StringRequest request=new StringRequest(
                Request.Method.POST,
                "http://"+MainActivity.ip+":90/mylaravel/public/api/Students/insert",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("Name",txtName.getText().toString());
                params.put("Phone",txtPhone.getText().toString());
                params.put("Email",txtEmail.getText().toString());
                params.put("Token",token);

                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);

    }
    public void fetchEmail(View view)
    {

        StringRequest request=new StringRequest(
                Request.Method.GET,
                "http://"+MainActivity.ip+":90/mylaravel/public/api/Students/fetchEmails",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            lst.clear();
                            JSONArray Jarr=new JSONArray(response);
                            for (int i=0; i<Jarr.length(); i++)
                            {
                                JSONObject jobj=Jarr.getJSONObject(i);
                                String email=jobj.getString("Email");
                                lst.add(email);

                            }
                            adapter.notifyDataSetChanged();

                        }
                        catch (Exception ex)
                        {
                            Toast.makeText(MainActivity.this, ""+ex.toString(), Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(request);

    }





    public void send_Notification(View view)
    {
        final String email=spinner.getSelectedItem().toString();
        final String title=txttitle.getText().toString();
        final String Message=txtmessage.getText().toString();


        StringRequest request=new StringRequest(
                Request.Method.POST,
                "http://"+MainActivity.ip+":90/mylaravel/public/api/Students/sendNotification",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("Email",email);
                params.put("Message",Message);
                params.put("Title",title);

                return params;
            }
        };

        requestQueue.add(request);


    }
}
