package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView=findViewById(R.id.listview);
        drawerLayout=findViewById(R.id.drawerLayout);
        final BlankFragment blankFragment=new BlankFragment();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                     // getSupportFragmentManager().beginTransaction().remove(blankFragment).commit();

                       for(Fragment fr:getSupportFragmentManager().getFragments())
                       {
                           getSupportFragmentManager().beginTransaction().remove(fr).commit();
                       }
                        drawerLayout.closeDrawers();
                        break;
                    case 1:

                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,blankFragment).commit();
                        drawerLayout.closeDrawers();

                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new FragmentLogin()).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new History()).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                }
            }

        });


    }
}
