package com.sanjaya.home24.di;

import com.sanjaya.home24.ui.selection.SelectionFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract SelectionFragment contributeSelectionFragment();
}
