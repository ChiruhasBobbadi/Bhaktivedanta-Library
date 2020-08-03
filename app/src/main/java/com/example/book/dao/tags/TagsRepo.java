package com.example.book.dao.tags;


import android.app.Application;

import com.example.book.database.Database;

class TagsRepo {
    Database db;
    private TagsDao dao;

    public TagsRepo(Application application) {

        db = Database.getInstance(application);

        dao = db.tagsDao();
    }


}
