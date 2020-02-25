package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {


    Spinner spinnerCountry,spinnerCity,spinnerTerritory;
    ArrayList<Areas> lst=new ArrayList<>();
    List<String> lstCountry=new ArrayList<>();
    List<String> lstCity=new ArrayList<>();
    List<String> lstTerritory=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadAreas();
        spinnerCountry=findViewById(R.id.spinner1);
        lstCountry=lst.stream().map(i->i.Country).distinct().collect(Collectors.toList());
        ArrayAdapter<String> adapterCountry=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lstCountry);
        spinnerCountry.setAdapter(adapterCountry);


        spinnerCity=findViewById(R.id.spinner2);
        spinnerTerritory=findViewById(R.id.spinner3);

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                lstCity=lst.stream().filter(i->i.Country.equals(lstCountry.get(position))).map(i->i.City).distinct().collect(Collectors.toList());
                ArrayAdapter<String> adapterCity=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,lstCity);
                spinnerCity.setAdapter(adapterCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                lstTerritory=lst.stream().filter(i->i.City.equals(lstCity.get(position))).map(i->i.Territory).collect(Collectors.toList());
                ArrayAdapter<String> adapterTerritory=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,lstTerritory);
                spinnerTerritory.setAdapter(adapterTerritory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private void LoadAreas()
    {
        lst.add(new Areas("Pakistan","Karachi","Malir"));
        lst.add(new Areas("Pakistan","Karachi","Johor"));
        lst.add(new Areas("Pakistan","Karachi","Liyari"));
        lst.add(new Areas("Pakistan","Karachi","Jahangeer Road"));
        lst.add(new Areas("Pakistan","Multan","Shah Rukn-e-azam"));
        lst.add(new Areas("Pakistan","Multan","Wabda town"));
        lst.add(new Areas("Pakistan","Multan","Ghanta Ghar"));
        lst.add(new Areas("Pakistan","Multan","Baha-u-din-Zakaria"));
        lst.add(new Areas("Pakistan","Sakhar","Sahd Belo Tample"));
        lst.add(new Areas("Pakistan","Sakhar","Soomar Gouth"));
        lst.add(new Areas("Pakistan","Sakhar","Lansdown Brig"));
        lst.add(new Areas("Pakistan","Sakhar","Bukkur"));

        lst.add(new Areas("Iraq","Baghdaad","Iraqi Musium"));
        lst.add(new Areas("Iraq","Baghdaad","Alkhidmia Mosque"));
        lst.add(new Areas("Iraq","Baghdaad","Dora"));
        lst.add(new Areas("Iraq","Baghdaad","Boratha Mosque"));
        lst.add(new Areas("Iraq","Karbala","Kufa"));
        lst.add(new Areas("Iraq","Karbala","Holy Shrine of imam Husain"));
        lst.add(new Areas("Iraq","Karbala","Altabria Square"));
        lst.add(new Areas("Iraq","Karbala","Albaladia Square"));
        lst.add(new Areas("Iraq","Najaf","Shrine Of Hazrat Ali"));
        lst.add(new Areas("Iraq","Najaf","Alatebaa"));
        lst.add(new Areas("Iraq","Najaf","Wadi Al-Salaam Cemetery"));
        lst.add(new Areas("Iraq","Najaf","Alfuraat"));






    }
}
