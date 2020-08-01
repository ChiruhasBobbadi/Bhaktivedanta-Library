package com.example.book.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.book.dao.level1.books.Level1_Books;
import com.example.book.dao.level2.pages.Level2_Pages;
import com.example.book.dao.level3.books.Level3_Books;
import com.example.book.dao.level3.books.Level3_BooksDao;
import com.example.book.dao.level3.pages.Level3_Pages;
import com.example.book.dao.level3.pages.Level3_PagesDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class L3Repo {
    Database db;
    private Level3_BooksDao l3dao;
    private Level3_PagesDao l3PDao;
    private static final String TAG = "L3Repo";
    public L3Repo(Application application) {

        db = Database.getInstance(application);

        l3dao = db.level3_booksDao();
        l3PDao = db.level3_pagesDao();
    }


    public LiveData<Integer> getCurrentPage(String book) {
        return l3dao.getCurrentPage(book);
    }


    public void updateCurrentPage(String book, int page) {

        Runnable runnable = () -> {
            db.level3_booksDao().updateCurrentPage(book, page);
        };
        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.submit(runnable);

    }


    public LiveData<Level3_Books> getBook(String book) {
        return l3dao.getBook(book);
    }


    public LiveData<List<Level3_Pages>> getPages(String book) {
        return l3PDao.getPages(book);
    }

    public LiveData<Integer> getPageNumberOfVerse(String book,String canto, String chapter, String verse) {
        return l3PDao.getPageNumberOfVerse(book,canto, chapter, verse);
    }


    public LiveData<List<String>> getChapters(String book,String canto) {
        return l3PDao.getChapters(book,canto);
    }

    //todo
    public LiveData<List<Integer>> getVerses(String book,String canto, String chapter) {
        return l3PDao.getVerses(book,canto, chapter);
    }


    public LiveData<List<String>> getCanto(String book) {
        return l3PDao.getCantos(book);
    }

}
