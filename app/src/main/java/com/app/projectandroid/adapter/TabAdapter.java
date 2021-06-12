package com.app.projectandroid.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.app.projectandroid.data.TabData;

import java.util.ArrayList;

public class TabAdapter extends FragmentStatePagerAdapter {

    ArrayList<TabData> data;

    public TabAdapter(FragmentManager fm, ArrayList<TabData> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position).getFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
