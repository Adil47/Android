package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Student> lst=new ArrayList<Student>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);

        lst.add(new Student("101","Sana","0200-9865321","sana@gmail.com",R.drawable.img));
        lst.add(new Student("102","Malik","0200-9865321","Malik@gmail.com",R.drawable.img2));
        lst.add(new Student("103","Abubakkar","0200-9865321","Abubakkar@gmail.com",R.drawable.img3));
        lst.add(new Student("104","Hassan","0200-9865321","Hassan@gmail.com",R.drawable.img4));
        lst.add(new Student("105","Ahsan","0200-9865321","Ahsan@gmail.com",R.drawable.img5));
        lst.add(new Student("106","Osama Haleem","0200-9865321","Osama@gmail.com",R.drawable.img6));
        lst.add(new Student("107","Hood Butt","0200-9865321","Hood@gmail.com",R.drawable.img7));
        lst.add(new Student("108","Rafay","0200-9865321","Rafay@gmail.com",R.drawable.img8));
        lst.add(new Student("101","Sana","0200-9865321","sana@gmail.com",R.drawable.img));
        lst.add(new Student("102","Malik","0200-9865321","Malik@gmail.com",R.drawable.img2));
        lst.add(new Student("103","Abubakkar","0200-9865321","Abubakkar@gmail.com",R.drawable.img3));
        lst.add(new Student("104","Hassan","0200-9865321","Hassan@gmail.com",R.drawable.img4));
        lst.add(new Student("105","Ahsan","0200-9865321","Ahsan@gmail.com",R.drawable.img5));
        lst.add(new Student("106","Osama Haleem","0200-9865321","Osama@gmail.com",R.drawable.img6));
        lst.add(new Student("107","Hood Butt","0200-9865321","Hood@gmail.com",R.drawable.img7));
        lst.add(new Student("108","Rafay","0200-9865321","Rafay@gmail.com",R.drawable.img8));


        StudentAdapter adapter=new StudentAdapter(this,lst);
        listView.setAdapter(adapter);



    }


}

class StudentAdapter extends BaseAdapter
{

    ArrayList<Student> lst;
    Context context;
    LayoutInflater inflater;

    public  StudentAdapter(Context context,ArrayList<Student> lst)
    {
        this.lst=lst;
        this.context=context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View v=inflater.inflate(R.layout.std_layout,null);
        ImageView img=v.findViewById(R.id.stdImage);
        TextView txtId=v.findViewById(R.id.stdId);
        TextView txtName=v.findViewById(R.id.stdName);
        TextView txtPhone=v.findViewById(R.id.stdPhone);
        TextView txtEmail=v.findViewById(R.id.stdEmail);

        Student obj=lst.get(i);
        img.setImageResource(obj.Image);
        txtId.setText(obj.Id);
        txtName.setText(obj.Name);
        txtPhone.setText(obj.Phone);
        txtEmail.setText(obj.Email);


        return v;
    }
}



