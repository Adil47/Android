package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {


    SearchView  searchView;
    ListView listView;
    ArrayList<String> lst;
    ArrayAdapter<String> adapter;
    List<String> lst2=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView=findViewById(R.id.seachview);
        listView=findViewById(R.id.lstView);
        lst=new ArrayList<>();
        lst.add("Abubakkar");
        lst.add("Hassan ud din");
        lst.add("Hood");
        lst.add("Osama saleem");
        lst.add("Osama Khushi");
        lst.add("Ahsan");
        lst.add("Malik");
        lst.add("Asad");
        lst.add("Hamza");
        lst.add("Builder");
        lst.add("Javed");
        lst.add("Arham");
        lst.add("Mohsin");

        for(String name:lst)
        {
           lst2.add(name);
        }
       adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lst2);
       listView.setAdapter(adapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {


                    lst2.clear();
//                    for(String name:lst)
//                    {
//                        if(name.toLowerCase().contains(s.toLowerCase()))
//                        {
//                            lst2.add(name);      Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_SHORT).show();
//                        }
//                    }
                // adapter.notifyDataSetChanged();

            lst2=    lst.stream().filter(i->i.toLowerCase().contains(s.toLowerCase())).collect(Collectors.toList());

                adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,lst2);
                listView.setAdapter(adapter);


                return false;
            }
        });



    }
}








