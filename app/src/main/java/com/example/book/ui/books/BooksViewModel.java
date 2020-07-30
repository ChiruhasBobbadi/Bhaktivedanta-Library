package com.example.book.ui.books;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.book.dao.lookup.LookUpDao;
import com.example.book.dao.lookup.LookupTable;
import com.example.book.database.L1BookRepo;

import java.util.List;

public class BooksViewModel extends AndroidViewModel {

    L1BookRepo repo;

    LiveData<List<LookupTable>> all;

    public BooksViewModel(Application application) {
        super(application);
        repo = new L1BookRepo(application);
        all = repo.getAllLevels();
    }

    public LiveData<List<LookupTable>> getAll(){
        return all;
    }


}