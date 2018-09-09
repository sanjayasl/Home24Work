package com.sanjaya.home24.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.sanjaya.home24.ui.selection.ArticleViewModel;
import com.sanjaya.home24.viewmodel.Home24ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel.class)
    abstract ViewModel bindArticleViewModel(ArticleViewModel articleViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(Home24ViewModelFactory factory);
}
