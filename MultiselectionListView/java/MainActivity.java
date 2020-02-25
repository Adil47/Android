package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Student> lst=new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);

        lst.add(new Student("abubakkar",false));
        lst.add(new Student("hassan uddin qureshi",false));
        lst.add(new Student("rafay",false));
        lst.add(new Student("osama haleem",false));
        lst.add(new Student("osama khushi",false));
        lst.add(new Student("zulkafal hood butt",true));
        lst.add(new Student("malik",false));
        lst.add(new Student("noman",false));
        lst.add(new Student("Hasnain",false));
        lst.add(new Student("abubakkar",false));
        lst.add(new Student("hassan uddin qureshi",false));
        lst.add(new Student("rafay",false));
        lst.add(new Student("osama haleem",false));
        lst.add(new Student("osama khushi",false));
        lst.add(new Student("zulkafal hood butt",true));
        lst.add(new Student("malik",false));
        lst.add(new Student("noman",false));
        lst.add(new Student("Hasnain",false));


        StudentAdapter studentAdapter=new StudentAdapter(lst,this);
        listView.setAdapter(studentAdapter);





    }

    public void getEligibleStudent(View view)
    {
        String str="";
        for(Student std:lst)
        {
            if(std.IsEligible)
            {
                str+=std.Name+"\n";
            }
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Eligible Students");
        builder.setMessage(str);
        builder.create().show();

    }


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
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {

            View v=inflater.inflate(R.layout.student_layout,null);
            CheckBox checkBox=v.findViewById(R.id.chbox);

         final    Student obj=lst.get(i);
            checkBox.setText(obj.Name);
            checkBox.setChecked(obj.IsEligible);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                    obj.IsEligible=b;
                }
            });

            return v;
        }
    }


}
