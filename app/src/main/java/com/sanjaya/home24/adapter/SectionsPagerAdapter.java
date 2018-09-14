package com.sanjaya.home24.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sanjaya.home24.ui.review.GridViewFragment;
import com.sanjaya.home24.ui.review.ListViewFragment;

import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> tabViews;

    public SectionsPagerAdapter(FragmentManager fm, List<Fragment> views) {
        super(fm);
        this.tabViews = views;
    }

    @Override
    public Fragment getItem(int position) {
        return tabViews.get(position);
    }

    @Override
    public int getCount() {
        return tabViews.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(tabViews.get(position) instanceof ListViewFragment){
            return "ListView";
        } else if(tabViews.get(position) instanceof GridViewFragment){
            return "GridView";
        } else {
            return "None";
        }
    }

    public List<Fragment> getTabViews() {
        return tabViews;
    }

}
