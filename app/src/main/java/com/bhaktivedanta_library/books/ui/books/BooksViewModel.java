package com.bhaktivedanta_library.books.ui.books;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bhaktivedanta_library.books.dao.lookup.LookupTable;
import com.bhaktivedanta_library.books.database.LookUpRepo;

import java.util.List;

public class BooksViewModel extends AndroidViewModel {

    LookUpRepo repo;

    LiveData<List<LookupTable>> all;

    public BooksViewModel(Application application) {
        super(application);
        repo = new LookUpRepo(application);
        all = repo.getAllLevels();
    }

    public LiveData<List<LookupTable>> getAll(){
        return all;
    }


}