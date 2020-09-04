package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Student> lst=new ArrayList<>();
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        listView = findViewById(R.id.listView);

        fetchStudents("");
        adapter = new StudentAdapter(lst, this);
        listView.setAdapter(adapter);


    }

    ///////////////////////////////// Option Menu  ////////////////////////////


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =(SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =(SearchView) menu.findItem(R.id.search).getActionView();
      //  searchView.setSearchableInfo( searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

               // Toast.makeText(StudentsActivity.this, s, Toast.LENGTH_SHORT).show();
                fetchStudents(s);

                return false;
            }
        });
        return true;
    }

    ////////////////////////////////////////// fetch Students  ////////////////////////////////////////////////////
    public void fetchStudents(String str)
    {
        StringRequest request=new StringRequest(
                Request.Method.GET,
                MainActivity.url+"Students/getStudents?str="+str,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    try {
                        lst.clear();
                        JSONArray jArr=new JSONArray(response);
                        for (int i =0; i<jArr.length();i++)
                        {
                            JSONObject jobj=jArr.getJSONObject(i);
                            String id=jobj.getString("Id");
                            String name=jobj.getString("Name");
                            String phone=jobj.getString("Phone");
                            String email=jobj.getString("Email");
                            String url=jobj.getString("Image");

                            Student std=new Student(id,name,phone,email,"123",url);
                            lst.add(std);

                        }
                        adapter.notifyDataSetChanged();

                    }catch(Exception e)
                    {

                    }
                       // new AlertDialog.Builder(StudentsActivity.this).setMessage(response).create().show();
                        //Toast.makeText(StudentsActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StudentsActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }


        );

        Volley.newRequestQueue(this).add(request);

    }
    /////////////////////////////////////// delete Student ////////////////////////////////////////
    public void deleteStudent(final String id)
    {
        StringRequest request=new StringRequest(
                Request.Method.POST,
                MainActivity.url+"Students/delete",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                          JSONObject jobj=new JSONObject(response);

                          String res=jobj.getString("Response");
                          if(res.equals("deleted"))
                          {
                             fetchStudents("");
                          }
                            Toast.makeText(StudentsActivity.this, ""+res, Toast.LENGTH_SHORT).show();

                        }catch(Exception e) {
                            Toast.makeText(StudentsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StudentsActivity.this, ""+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }


        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("StudentId",id);

                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);

    }

    //////////////////////////////////////////////////////////////////////////


class  StudentAdapter extends BaseAdapter
{
    ArrayList<Student> lst;
    Context context;
    LayoutInflater inflater;

    public StudentAdapter(ArrayList<Student> lst, Context context) {
        this.lst = lst;
        this.context = context;
        this.inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v=inflater.inflate(R.layout.std_layout,null);
        TextView txtId=v.findViewById(R.id.std_id);
        TextView txtName=v.findViewById(R.id.std_name);
        TextView txtPhone=v.findViewById(R.id.std_phone);
        TextView txtEmail=v.findViewById(R.id.std_email);
        TextView txtPassword=v.findViewById(R.id.std_password);

        ImageView stdimg=v.findViewById(R.id.std_img);
        ImageView imgEdit=v.findViewById(R.id.std_img_edit);
        ImageView imgView=v.findViewById(R.id.std_img_view);
        ImageView imgDelete=v.findViewById(R.id.std_img_delete);


       final Student obj=lst.get(i);

        Picasso.with(context).load(obj.ImgUrl).into(stdimg);
        txtId.setText(obj.Id);
        txtName.setText(obj.Name);
        txtPhone.setText(obj.Phone);
        txtEmail.setText(obj.Email);
        txtPassword.setText(obj.Password);
//------------------------  Delete record   -----------------
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Delete Record");
                builder.setMessage("Are you sure do you want to delete this record?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                            deleteStudent(obj.Id);


                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });

//-----------------------  end  ----------------------
//-----------------------  Update Record  ----------------------

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,MainActivity.class);
                intent.putExtra("Id",obj.Id);
                intent.putExtra("Name",obj.Name);
                intent.putExtra("Phone",obj.Phone);
                intent.putExtra("Email",obj.Email);
                intent.putExtra("Password",obj.Password);
                intent.putExtra("img_url",obj.ImgUrl);

                startActivity(intent);

            }
        });

//------------------------------- end ----------------------------

//------------------------------ View Data  -----------------------------

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,ViewActivity.class);
                intent.putExtra("Id",obj.Id);
                intent.putExtra("Name",obj.Name);
                intent.putExtra("Phone",obj.Phone);
                intent.putExtra("Email",obj.Email);
                intent.putExtra("Password",obj.Password);
                intent.putExtra("Img",obj.ImgUrl);

                startActivity(intent);

            }
        });
//-------------------------------------------------------------------






        return v;
    }
}


}