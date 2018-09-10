package com.sanjaya.home24.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sanjaya.home24.AppExecutors;
import com.sanjaya.home24.api.ApiResponse;
import com.sanjaya.home24.api.ArticleResponse;
import com.sanjaya.home24.api.Home24Service;
import com.sanjaya.home24.dao.ArticleDao;
import com.sanjaya.home24.datamodel.Article;
import com.sanjaya.home24.datamodel.Resource;
import com.sanjaya.home24.db.Home24Db;
import com.sanjaya.home24.util.AbsentLiveData;
import com.sanjaya.home24.util.RateLimiter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ArticleRepository {

    private final Home24Db db;

    private final ArticleDao articleDao;

    private final Home24Service home24Service;

    private final AppExecutors appExecutors;

    private RateLimiter<String> repoListRateLimit = new RateLimiter<>(10, TimeUnit.MINUTES);

    @Inject
    public ArticleRepository(Home24Db db, ArticleDao articleDao, Home24Service home24Service,
                             AppExecutors appExecutors) {
        this.db = db;
        this.articleDao = articleDao;
        this.home24Service = home24Service;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<List<Article>>> loadArticles(){
        return new NetworkBoundResource<List<Article>, ArticleResponse>(appExecutors) {
            @Override
            protected ArticleResponse processResponse(ApiResponse<ArticleResponse> response) {
                // return super.processResponse(response);
                ArticleResponse body = response.body;
                if(body != null) {
                    //body.set
                }
                return body;
            }

            @Override
            protected void saveCallResult(@NonNull ArticleResponse item) {
                db.beginTransaction();
                try{
                    articleDao.insertArticles(item.getEmbedded().getArticles());
                    db.setTransactionSuccessful();
                }finally {
                    db.endTransaction();
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Article> data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<List<Article>> loadFromDb() {
                return Transformations.switchMap(articleDao.loadArticles(), articleData -> {
                    if(articleData == null || articleData.size() == 0){
                        return AbsentLiveData.create();
                    } else {
                        MutableLiveData<List<Article>> data = new MutableLiveData<List<Article>>();
                        data.setValue(articleData);
                        return data;
                    }
                });
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<ArticleResponse>> createCall() {
                return home24Service.getArticles(1, "de_DE", 10);
            }
        }.asLiveData();
    }
}
