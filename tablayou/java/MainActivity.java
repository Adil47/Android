package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);

        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());

        adapter.add(new BlankFragment(),"First");
        adapter.add(new BlankFragment2(),"Second");
        adapter.add(new BlankFragment(),"Third");
        adapter.add(new BlankFragment2(),"Fourth");
        adapter.add(new BlankFragment(),"Fifth");
        adapter.add(new BlankFragment2(),"Sixth");
        adapter.add(new BlankFragment(),"Seventh");
        adapter.add(new BlankFragment2(),"Eight");
        adapter.add(new BlankFragment(),"Nineth");
        adapter.add(new BlankFragment2(),"Tenth");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}




class  ViewPagerAdapter extends FragmentPagerAdapter
{

    ArrayList<Fragment> lstFragments=new ArrayList<>();
    ArrayList<String> lstTitles=new ArrayList<>();

    public void add(Fragment fm,String title)
    {
        lstFragments.add(fm);
        lstTitles.add(title);
    }

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return lstFragments.get(position);
    }

    @Override
    public int getCount() {
        return lstFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lstTitles.get(position);
    }
}








