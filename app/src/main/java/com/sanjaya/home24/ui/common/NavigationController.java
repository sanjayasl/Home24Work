package com.sanjaya.home24.ui.common;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;

import com.sanjaya.home24.MainActivity;
import com.sanjaya.home24.R;
import com.sanjaya.home24.ui.review.ReviewFragment;
import com.sanjaya.home24.ui.selection.SelectionFragment;

import javax.inject.Inject;

public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;
    private final ActionBar actionbar;
    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
        this.actionbar = mainActivity.getSupportActionBar();
    }

    public void setBackStackChangeListener(FragmentManager.OnBackStackChangedListener listener){
        this.fragmentManager.addOnBackStackChangedListener(listener);
    }

    public void navigationToSelection(){
        SelectionFragment selectionFragment = new SelectionFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, selectionFragment)
                // .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigationToReview(){
        enableUpNavigation(true);
        ReviewFragment reviewFragment = new ReviewFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, reviewFragment)
                .commitAllowingStateLoss();
    }

    public void enableUpNavigation(boolean canBack){
        actionbar.setDisplayHomeAsUpEnabled(canBack);
    }

    public void shouldDisplayHomeUp(){
        boolean canBack = fragmentManager.getBackStackEntryCount() > 1;
        actionbar.setDisplayHomeAsUpEnabled(canBack);
    }

    public void popBackStack(){
        this.fragmentManager.popBackStackImmediate();
    }

}
