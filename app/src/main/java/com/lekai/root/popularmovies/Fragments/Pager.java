package com.lekai.root.popularmovies.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by root on 5/3/17.
 */

public class Pager extends FragmentStatePagerAdapter {
    int tabCount;

    public Pager(FragmentManager fm, int myTabCount) {
        super(fm);
        this.tabCount = myTabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                OverviewTab tab1 = new OverviewTab();
                return tab1;
            case 1:
                TrailerTab tab2 = new TrailerTab();
                return tab2;
            case 2:
                ReviewTab tab3 = new ReviewTab();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
