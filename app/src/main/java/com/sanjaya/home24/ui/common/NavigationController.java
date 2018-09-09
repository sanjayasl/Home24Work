package com.sanjaya.home24.ui.common;

import android.support.v4.app.FragmentManager;

import com.sanjaya.home24.MainActivity;
import com.sanjaya.home24.R;
import com.sanjaya.home24.ui.selection.SelectionFragment;

import javax.inject.Inject;

public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;
    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigationToSelection(){
        SelectionFragment selectionFragment = new SelectionFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, selectionFragment)
                .commitAllowingStateLoss();
    }

}
