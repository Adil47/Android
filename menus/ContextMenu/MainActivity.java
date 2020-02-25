package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> lst;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listView);

        lst=new ArrayList<>();
        lst.add("Abu bakkar");
        lst.add("Hassan ud din Qureshi");
        lst.add("Ahsan Khan");
        lst.add("Osama Haleem");
        lst.add("Rafay Asif");
        lst.add("Zulkafal Hood butt");
        lst.add("Shafay ");
        lst.add("Abu bakkar");
        lst.add("Shafay ");


        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lst);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_items,menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {


        if(item.getTitle().toString().equals("Delete"))
        {
          AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            lst.remove( info.position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Remove Successfully", Toast.LENGTH_SHORT).show();

        }
        else  Toast.makeText(this, ""+item.getTitle().toString(), Toast.LENGTH_SHORT).show();

        return super.onContextItemSelected(item);
    }
}
