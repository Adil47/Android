package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText txtName,txtPhone,txtEmail;
    String id="";
    ListView listView;
    ArrayList<Student> lst=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName=findViewById(R.id.txtName);
        txtPhone=findViewById(R.id.txtPhone);
        txtEmail=findViewById(R.id.txtEmail);
        listView=findViewById(R.id.listView);
        // Write a message to the database
         database = FirebaseDatabase.getInstance();
         myRef = database.getReference("Students");

      //   myRef.child("StudentId").setValue("Hello, World!");

        final StudentAdapter adapter=new StudentAdapter(lst,this);
        listView.setAdapter(adapter);

         myRef.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // String str="";
                 lst.clear();
                 for(DataSnapshot snapshot:dataSnapshot.getChildren())
                 {
                     String stdId=snapshot.getKey();
                     String Name=snapshot.child("Name").getValue(String.class);
                     String Phone=snapshot.child("Phone").getValue(String.class);
                     String Email=snapshot.child("Email").getValue(String.class);

                     lst.add(new Student(stdId,Name,Phone,Email));
//                     str+=stdId+"\n";
//                     str+=Name+"\n";
//                     str+=Phone+"\n";
//                     str+=Email+"\n\n";

                 }
                 adapter.notifyDataSetChanged();

                // Toast.makeText(MainActivity.this, ""+str, Toast.LENGTH_LONG).show();
             }


             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });







    }

    public void insert(View view)
    {
        String name=txtName.getText().toString();
        String phone=txtPhone.getText().toString();
        String email=txtEmail.getText().toString();

        Student std=new Student("101",name,phone,email);
        myRef.push().setValue(std);


    }

    public void update(View view) {
       DatabaseReference stdRef= myRef.child(id).getRef();
        stdRef.child("Name").getRef().setValue(txtName.getText().toString());
        stdRef.child("Phone").getRef().setValue(txtPhone.getText().toString());
        stdRef.child("Email").getRef().setValue(txtEmail.getText().toString());


    }


    ///////////////// Studentt Adapter  /////////////////

    class StudentAdapter extends BaseAdapter
    {
        ArrayList<Student> lst;
        Context context;
        LayoutInflater inflater;

        public StudentAdapter(ArrayList<Student> lst, Context context) {
            this.lst = lst;
            this.context = context;
            inflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
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
            TextView txt_id=v.findViewById(R.id.std_id);
            TextView txt_name=v.findViewById(R.id.std_name);
            TextView txt_phone=v.findViewById(R.id.std_phone);
            TextView txt_email=v.findViewById(R.id.std_email);

            final Student std=lst.get(i);

            txt_id.setText(std.StudentId);
            txt_name.setText(std.Name);
            txt_phone.setText(std.Phone);
            txt_email.setText(std.Email);

            ImageView imgDelete=v.findViewById(R.id.img_delete);
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myRef.child(std.StudentId).getRef().removeValue();
                    Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                }
            });
            ImageView imgEdit=v.findViewById(R.id.img_edit);
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    txtName.setText(std.Name);
                    txtPhone.setText(std.Phone);
                    txtEmail.setText(std.Email);
                    id=std.StudentId;




                }
            });



            return v;
        }
    }






}
