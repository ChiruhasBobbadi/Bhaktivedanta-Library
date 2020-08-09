package com.example.book.ui.Level2.books;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.book.dao.level1.books.Level1_Books;
import com.example.book.dao.level2.pages.Level2_Pages;
import com.example.book.database.L2Repo;

import java.util.List;

public class L2ViewModel extends AndroidViewModel {
    private static final String TAG = "L2ViewModel";
    L2Repo repo;

    public L2ViewModel(@NonNull Application application) {
        super(application);
        repo = new L2Repo(application);
    }


    public LiveData<Integer> getCurrentPage(String book) {
        return repo.getCurrentPage(book);
    }


    public void updateCurrentPage(String book, int page) {
        Log.d(TAG, "updateCurrentPage: " + page);
        repo.updateCurrentPage(book, page);
    }


    public LiveData<Level1_Books> getBook(String book) {
        return repo.getBook(book);
    }

    public LiveData<List<Level2_Pages>> getPages(String book) {

        return repo.getPages(book);

    }



}
