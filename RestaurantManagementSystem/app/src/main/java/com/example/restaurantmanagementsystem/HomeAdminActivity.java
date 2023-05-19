package com.example.restaurantmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.restaurantmanagementsystem.fragment.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeAdminActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager2 sViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        navigationView = findViewById(R.id.bottomView);
        sViewPager = findViewById(R.id.ViewPager2);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        sViewPager.setAdapter(adapter);

        sViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        navigationView.getMenu().findItem(R.id.menuhome).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.menumanage).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.menuaccount).setChecked(true);
                        break;
                }
            }
        });

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menuhome){
                    sViewPager.setCurrentItem(0);

                }else if (id == R.id.menumanage){
                    sViewPager.setCurrentItem(1);

                }else if (id == R.id.menuaccount){
                    sViewPager.setCurrentItem(2);

                }
                return true;
            }
        });
    }
}