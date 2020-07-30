package com.example.book.ui.l1Books;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.book.dao.level1.books.Level1_Books;
import com.example.book.dao.level1.books.Level1_BooksDao;
import com.example.book.dao.level1.pages.Level1_Pages;
import com.example.book.dao.level1.pages.Level1_PagesDao;
import com.example.book.database.L1BookRepo;

import java.util.List;

public class L1ViewModel extends AndroidViewModel implements Level1_BooksDao, Level1_PagesDao {
    private static final String TAG = "L1ViewModel";
    L1BookRepo repo;
    public L1ViewModel(@NonNull Application application) {
        super(application);
        repo = new L1BookRepo(application);
    }

    @Override
    public LiveData<Integer> getCurrentPage(String book) {
        return repo.getCurrentPage(book);
    }

    @Override
    public void updateCurrentPage(String book, int page) {
        repo.updateCurrentPage(book,page);
    }

    @Override
    public LiveData<Level1_Books> getBook(String book) {
        return repo.getBook(book);
    }

    @Override
    public LiveData<List<Level1_Pages>> getPages(String book) {
        LiveData<List<Level1_Pages>> a= repo.getPages(book);
        //Log.d(TAG, "getPages: "+a.getValue().size());
        return a;
    }
}
