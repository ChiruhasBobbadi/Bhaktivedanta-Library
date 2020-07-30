package com.example.book.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.book.dao.level1.books.Level1_Books;
import com.example.book.dao.level1.books.Level1_BooksDao;
import com.example.book.dao.lookup.LookUpDao;
import com.example.book.dao.lookup.LookupTable;

import java.util.List;

public class L1BookRepo {
    private Level1_BooksDao l1dao;

    private LookUpDao lookUpDao;
    Database db;

    private LiveData<List<LookupTable>> all;

    //private LiveData<List<Level1_Books>> allBooks;
    public L1BookRepo(Application application){

        db = Database.getInstance(application);

        lookUpDao = db.lookUpDao();
        all = lookUpDao.getAll();
    }


    public LiveData<List<LookupTable>> getAllLevels(){
        return all;
    }

    // define all the possible operations here

    // these operations will be visible to view



}
