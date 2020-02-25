package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    // ----------- Option Menu start working
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getTitle().toString())
        {
            case "Home" :
                Intent intent=new Intent(this,HomeActivity.class);
                startActivity(intent);
                break;
        }

        Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();


        return super.onOptionsItemSelected(item);
    }


    // ----------- Option Menu working End
     public void popup_menu(View view)
     {
         PopupMenu popupMenu=new PopupMenu(this,view);
         popupMenu.getMenuInflater().inflate(R.menu.option_menu,popupMenu.getMenu());
         popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
             @Override
             public boolean onMenuItemClick(MenuItem item) {

                 switch (item.getTitle().toString())
                 {
                     case "Home" :
                         Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                         startActivity(intent);
                         break;
                 }


                 Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                 return false;
             }
         });
         popupMenu.show();

     }
}
