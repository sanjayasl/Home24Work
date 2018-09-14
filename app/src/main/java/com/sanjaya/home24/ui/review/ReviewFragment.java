package com.sanjaya.home24.ui.review;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanjaya.home24.R;
import com.sanjaya.home24.adapter.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabLayout.OnTabSelectedListener mListener;

    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * inflate fragment_tab_layout and setup view
         */
        View rootView = inflater.inflate(R.layout.fragment_review, null);
        mViewPager = (ViewPager) rootView.findViewById(R.id.container);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.tabs);

        //mPref = getContext().getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);

        if(mSectionsPagerAdapter == null)
            mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), getTabViews());
        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                mTabLayout.setupWithViewPager(mViewPager);
            }
        });

        //mTabLayout.addOnTabSelectedListener(mListener);

        return  rootView;
    }

    private List<Fragment> getTabViews(){
        List<Fragment> views = new ArrayList<>();
        views.add(new ListViewFragment());
        views.add(new GridViewFragment());
        return  views;
    }

}
