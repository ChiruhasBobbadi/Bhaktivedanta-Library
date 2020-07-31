package com.example.book.ui.Level3.Books;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.book.dao.level1.books.Level1_Books;
import com.example.book.dao.level2.pages.Level2_Pages;
import com.example.book.dao.level3.books.Level3_Books;
import com.example.book.dao.level3.pages.Level3_Pages;
import com.example.book.database.L2Repo;
import com.example.book.database.L3Repo;

import java.util.List;

public class L3ViewModel extends AndroidViewModel {
    L3Repo repo;
    private static final String TAG = "L3ViewModel";

    public L3ViewModel(@NonNull Application application) {
        super(application);
        repo = new L3Repo(application);
    }


    public LiveData<Integer> getCurrentPage(String book) {
        return repo.getCurrentPage(book);
    }


    public void updateCurrentPage(String book, int page) {
        Log.d(TAG, "updateCurrentPage: " + page);
        repo.updateCurrentPage(book, page);
    }


    public LiveData<Level3_Books> getBook(String book) {
        return repo.getBook(book);
    }

    public LiveData<List<Level3_Pages>> getPages(String book) {

        return repo.getPages(book);

    }
}
