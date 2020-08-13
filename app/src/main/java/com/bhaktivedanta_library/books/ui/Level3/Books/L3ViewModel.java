package com.bhaktivedanta_library.books.ui.Level3.Books;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bhaktivedanta_library.books.dao.level3.books.Level3_Books;
import com.bhaktivedanta_library.books.dao.level3.pages.Level3_Pages;
import com.bhaktivedanta_library.books.database.L3Repo;

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
