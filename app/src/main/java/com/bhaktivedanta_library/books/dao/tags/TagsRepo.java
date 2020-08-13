package com.bhaktivedanta_library.books.dao.tags;


import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bhaktivedanta_library.books.database.PersistentDB;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TagsRepo {
    private static final String TAG = "TagsRepo";
    PersistentDB db;
    private TagsDao dao;

    public TagsRepo(Application application) {

        db = PersistentDB.getInstance(application);
        dao = db.tagsDao();
    }


    LiveData<Boolean> checkTag(String tagName, String bookName, String lastLevel) {
        return dao.checkTag(tagName, bookName, lastLevel);
    }

    public void addTag(Tags tag) {
        Runnable runnable = () -> {
            db.tagsDao().addTag(tag);
        };
        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.submit(runnable);
    }

    public void deleteTag(String tagName, String bookName, String lastLevel) {
        Runnable runnable = () -> {
            db.tagsDao().deleteTag(tagName, bookName, lastLevel);
        };
        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.submit(runnable);
    }

    public LiveData<List<String>> getTagsOfPage(String bookName, String lastLevel,int page) {
        return dao.getTagsOfPage(bookName, lastLevel,page);
    }

    public LiveData<List<Tags>> getAll() {
        return dao.getAll();
    }

    public LiveData<List<Tags>> getTagsByName(String tagName) {
        return dao.getTagsByName(tagName);
    }


}
