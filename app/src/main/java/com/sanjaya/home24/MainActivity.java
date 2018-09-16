package com.sanjaya.home24;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sanjaya.home24.constant.AppValue;
import com.sanjaya.home24.ui.common.NavigationController;
import com.sanjaya.home24.ui.common.SharedPeferenceController;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.sanjaya.home24.constant.AppValue.CURRENT_REVIEW;
import static com.sanjaya.home24.constant.AppValue.PREF_ARTICAL_COUNT;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector, FragmentManager.OnBackStackChangedListener{
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    NavigationController navigationController;
    @Inject
    SharedPeferenceController sharedPeferenceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // navigationController.setBackStackChangeListener(this);
        // navigationController.shouldDisplayHomeUp();
        if(savedInstanceState == null){
            navigationController.navigationToSelection();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onBackStackChanged() {
//        navigationController.shouldDisplayHomeUp();
    }

    @Override
    public void onBackPressed() {
        if(sharedPeferenceController.getIntegerValue(PREF_ARTICAL_COUNT) > 0){
            onSupportNavigateUp();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if(!sharedPeferenceController.getBooleanValue(CURRENT_REVIEW)) {
            int currrentVal = sharedPeferenceController.getIntegerValue(PREF_ARTICAL_COUNT);
            sharedPeferenceController.saveIntegerValue(PREF_ARTICAL_COUNT, currrentVal - 1);
        } else {
            sharedPeferenceController.saveBooleanValue(CURRENT_REVIEW, false);
        }
//        navigationController.popBackStack();
        navigationController.navigationToSelection();
        return true;
    }

}
