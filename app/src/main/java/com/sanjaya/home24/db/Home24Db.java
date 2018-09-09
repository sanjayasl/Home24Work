package com.sanjaya.home24.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sanjaya.home24.dao.ArticleDao;
import com.sanjaya.home24.datamodel.Article;

/**
 * Main database description.
 */
@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class Home24Db extends RoomDatabase {

    abstract public ArticleDao articleDao();
}
