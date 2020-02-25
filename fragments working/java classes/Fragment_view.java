package com.example.myapplication;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_view extends Fragment {

Context context;
    public Fragment_view(Context context) {
       this.context=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View v=inflater.inflate(R.layout.fragment_fragment_view, container, false);;

        ListView listView=v.findViewById(R.id.listview);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,MainActivity.lst);
        listView.setAdapter(adapter);


        return v;
    }

}
