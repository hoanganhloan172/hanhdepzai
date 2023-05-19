package com.example.restaurantmanagementsystem.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerUserAdapter extends FragmentStatePagerAdapter {
    public ViewPagerUserAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new UserHomeFragment();
            case 1:
                return new ProfileFragment();
            default:
                return new UserHomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
