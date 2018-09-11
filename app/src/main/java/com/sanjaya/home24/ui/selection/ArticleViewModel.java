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

    private final ArticleRepository repository;
    private final LiveData<Resource<List<Article>>> articles;

    @Inject
    ArticleViewModel(ArticleRepository repository){
        this.repository = repository;
        this.articles = repository.loadArticles();
    }

    public LiveData<Resource<List<Article>>> getArticles() { return articles;}

    public LiveData<Resource<Article>> getArticle(int position) {
        MutableLiveData<Resource<Article>> articleLiveData = new MutableLiveData<>();
        articleLiveData.setValue(Resource.success(articles.getValue().data.get(position)));
        return articleLiveData;
    }

    public void updateArticleWithLike(int position, boolean isLike){
        repository.updateLike(articles.getValue().data.get(position).getSku(), isLike);
    }

    public void updateArticleWithDislike(int position, boolean isDislike){
        repository.updateDislike(articles.getValue().data.get(position).getSku(), isDislike);
    }

}
