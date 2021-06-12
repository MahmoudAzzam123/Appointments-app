package com.app.projectandroid.data;

import androidx.fragment.app.Fragment;

public class TabData {

    private String title;
    private Fragment fragment;

    public TabData() {
    }

    public TabData(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
