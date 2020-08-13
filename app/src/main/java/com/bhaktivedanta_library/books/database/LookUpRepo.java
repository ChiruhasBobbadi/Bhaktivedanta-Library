package com.bhaktivedanta_library.books.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bhaktivedanta_library.books.dao.lookup.LookUpDao;
import com.bhaktivedanta_library.books.dao.lookup.LookupTable;

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
