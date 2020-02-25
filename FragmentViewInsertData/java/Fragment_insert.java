package com.example.fragmentviewinsertdata;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_insert extends Fragment {

    Context context;

    public Fragment_insert(Context context)
    {
        this.context=context;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.fragment_fragment_insert, container, false);

        final EditText  txtName = v.findViewById(R.id.txtName);
        final EditText  txtPhone = v.findViewById(R.id.txtPhone);
        final EditText  txtEmail = v.findViewById(R.id.txtEmail);

        Button addrecord = v.findViewById(R.id.btninsertrecord);
        addrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = "Name : "+txtName.getText().toString()+"\nPhone : "+txtPhone.getText().toString()+"\nEmail : "+txtEmail.getText().toString()+"\n\n";
                MainActivity.lst.add(str);

                Toast.makeText(context, "Add Successfully", Toast.LENGTH_SHORT).show();

            }
        });


        return v;
    }

}
