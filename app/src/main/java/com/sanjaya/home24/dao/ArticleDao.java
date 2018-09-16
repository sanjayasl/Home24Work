package com.sanjaya.home24.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.sanjaya.home24.datamodel.Article;

import java.util.List;

@Dao
public abstract class ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(Article... articles);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertArticles(List<Article> articles);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long createArticleIfNotExists(Article article);

    @Query("SELECT * FROM Article")
    public abstract LiveData<List<Article>> loadArticles();

    @Query("UPDATE Article SET like = :isLike, dislike = :dislike WHERE sku = :sku")
    public abstract void updateLike(String sku, boolean isLike, boolean dislike);

    @Query("UPDATE Article SET dislike = :isDisLike, like = :like WHERE sku = :sku")
    public abstract void updateDisLike(String sku, boolean isDisLike, boolean like);

}
