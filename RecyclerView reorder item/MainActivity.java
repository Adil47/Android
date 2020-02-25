package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerview);
        lst=new ArrayList<>();
        lst.add("Osama Khushi");
        lst.add("hassan uddin qureshi");
        lst.add("Abubakkar");
        lst.add("Osama Haleem");
        lst.add("Ahsan");
        lst.add("Zulkafal Hood But");
        lst.add("Osama Khushi");
        lst.add("hassan uddin qureshi");
        lst.add("Abubakkar");
        lst.add("Osama Haleem");
        lst.add("Ahsan");
        lst.add("Zulkafal Hood But");
        lst.add("Osama Khushi");
        lst.add("hassan uddin qureshi");
        lst.add("Abubakkar");


        RecyclerView.LayoutManager manager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        final MyRecyclerViewAdapter adapter=new MyRecyclerViewAdapter(lst);
        recyclerView.setAdapter(adapter);


        ItemTouchHelper helper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT| ItemTouchHelper.DOWN,0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder draged, @NonNull RecyclerView.ViewHolder target) {

                int dragedPosition= draged.getAdapterPosition();
                int targetPosition= target.getAdapterPosition();

                Collections.swap(lst,dragedPosition,targetPosition);
                adapter.notifyItemMoved(dragedPosition,targetPosition);

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });

        helper.attachToRecyclerView(recyclerView);


    }
}
class  MyRecyclerViewAdapter extends  RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>
{

    ArrayList<String> lst;

    public MyRecyclerViewAdapter(ArrayList<String> lst) {
        this.lst = lst;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name=lst.get(position);

        holder.txtName.setText(name);
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public  class  MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.textview);

        }
    }
}