package com.sanjaya.home24.api;

import android.arch.lifecycle.LiveData;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Home24Service {

    @GET(UrlManager.ARTICALES)
    LiveData<ApiResponse<ArticleResponse>> getArticles(
            @Query("appDomain") int appDomain,
            @Query("locale") String locale,
            @Query("limit") int limit);

}
