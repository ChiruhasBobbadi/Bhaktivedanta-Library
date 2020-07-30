package com.example.book.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.book.dao.level1.books.Level1_Books;
import com.example.book.dao.level1.books.Level1_BooksDao;
import com.example.book.dao.level1.pages.Level1_Pages;
import com.example.book.dao.level1.pages.Level1_PagesDao;
import com.example.book.dao.lookup.LookupTable;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class L1BookRepo implements Level1_BooksDao, Level1_PagesDao {
    Database db;
    private Level1_BooksDao l1dao;
    private Level1_PagesDao l1PDao;

    public L1BookRepo(Application application) {

        db = Database.getInstance(application);

        l1dao = db.level1_booksDao();
        l1PDao =db.level1_pagesDao();
    }

    @Override
    public LiveData<Integer> getCurrentPage(String book) {
        return l1dao.getCurrentPage(book);
    }

    @Override
    public void updateCurrentPage(String book, int page) {

        Runnable runnable = () -> {
            db.level1_booksDao().updateCurrentPage(book,page); //Replace this by your request
        };
        ExecutorService ex  = Executors.newSingleThreadExecutor();
        ex.submit(runnable);

    }

    @Override
    public LiveData<Level1_Books> getBook(String book) {
        return l1dao.getBook(book);
    }

    @Override
    public LiveData<List<Level1_Pages>> getPages(String book) {
        return l1PDao.getPages(book);
    }


    // define all the possible operations here

    // these operations will be visible to view


}
