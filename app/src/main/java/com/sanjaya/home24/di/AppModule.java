package com.sanjaya.home24.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.sanjaya.home24.api.Home24Service;
import com.sanjaya.home24.api.UrlManager;
import com.sanjaya.home24.dao.ArticleDao;
import com.sanjaya.home24.db.Home24Db;
import com.sanjaya.home24.util.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {
    @Singleton
    @Provides
    Home24Service provideHome24Service() {
        OkHttpClient client =
                new OkHttpClient.Builder()
                        //.addInterceptor(
                        //       new ErrorInterceptor())
                        .addNetworkInterceptor(
                                new StethoInterceptor())
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(UrlManager.API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(Home24Service.class);
    }

    @Singleton @Provides
    Home24Db provideDb(Application app) {
        return Room.databaseBuilder(app, Home24Db.class,"home24.db").build();
    }

    @Singleton @Provides
    ArticleDao provideArticleDao(Home24Db db) { return db.articleDao(); }

}
