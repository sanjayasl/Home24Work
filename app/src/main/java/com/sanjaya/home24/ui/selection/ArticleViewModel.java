package com.sanjaya.home24.ui.selection;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sanjaya.home24.datamodel.Article;
import com.sanjaya.home24.datamodel.Resource;
import com.sanjaya.home24.repository.ArticleRepository;

import java.util.List;

import javax.inject.Inject;

public class ArticleViewModel extends ViewModel {

    private final LiveData<Resource<List<Article>>> articles;

    @Inject
    ArticleViewModel(ArticleRepository repository){
        articles = repository.loadArticles();
    }

    public LiveData<Resource<List<Article>>> getArticles() { return articles;}

    //public LiveData<Resource<Article>> getArticle(int position) { return articles.getValue().data.get(position); }
}
