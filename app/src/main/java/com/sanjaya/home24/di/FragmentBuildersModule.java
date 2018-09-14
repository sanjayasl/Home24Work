package com.sanjaya.home24.di;

import com.sanjaya.home24.ui.review.ListViewFragment;
import com.sanjaya.home24.ui.review.ReviewFragment;
import com.sanjaya.home24.ui.selection.SelectionFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract SelectionFragment contributeSelectionFragment();

//    @ContributesAndroidInjector
//    abstract ReviewFragment contributeReviewFragment();

    @ContributesAndroidInjector
    abstract ListViewFragment contributeListViewFragment();
}
