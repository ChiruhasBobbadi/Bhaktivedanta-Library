package com.example.book.ui.Level1.Books;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.book.dao.level1.books.Level1_Books;
import com.example.book.dao.level1.pages.Level1_Pages;
import com.example.book.database.L1Repo;

import java.util.List;

public class L1ViewModel extends AndroidViewModel  {
    private static final String TAG = "L1ViewModel";
    L1Repo repo;

    public L1ViewModel(@NonNull Application application) {
        super(application);
        repo = new L1Repo(application);
    }


    public LiveData<Integer> getCurrentPage(String book) {
        return repo.getCurrentPage(book);
    }


    public void updateCurrentPage(String book, int page) {
        Log.d(TAG, "updateCurrentPage: "+page);
        repo.updateCurrentPage(book, page);
    }


    public LiveData<Level1_Books> getBook(String book) {
        return repo.getBook(book);
    }


    public LiveData<List<Level1_Pages>> getPages(String book) {

        return repo.getPages(book);

    }

//    @Override
//    public void setBookmark(String book, int val) {
//
//    }
}
