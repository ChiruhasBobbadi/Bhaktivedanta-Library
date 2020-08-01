package com.example.book.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.book.dao.lookup.LookUpDao;
import com.example.book.dao.lookup.LookupTable;

import java.util.List;

public class LookUpRepo {
    private LookUpDao lookUpDao;
    Database db;

    private LiveData<List<LookupTable>> all;

    public LookUpRepo(Application application){

        db = Database.getInstance(application);

        lookUpDao = db.lookUpDao();
        all = lookUpDao.getAll();

    }

    public LiveData<List<LookupTable>> getAllLevels(){
        return all;
    }

    public LiveData<List<String>> getBooks(){
        return  lookUpDao.getBooks();
    }
    public LiveData<Integer> getLevel(String book){
        return  lookUpDao.getLevel(book);
    }
}
