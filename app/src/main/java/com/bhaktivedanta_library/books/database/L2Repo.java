package com.bhaktivedanta_library.books.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bhaktivedanta_library.books.dao.level1.books.Level1_Books;
import com.bhaktivedanta_library.books.dao.level2.books.Level2_BooksDao;
import com.bhaktivedanta_library.books.dao.level2.pages.Level2_Pages;
import com.bhaktivedanta_library.books.dao.level2.pages.Level2_PagesDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class L2Repo {
    Database db;
    private Level2_BooksDao l2dao;
    private Level2_PagesDao l2PDao;

    public L2Repo(Application application) {

        db = Database.getInstance(application);

        l2dao = db.level2_booksDao();
        l2PDao = db.level2_pagesDao();
    }


    public LiveData<Integer> getCurrentPage(String book) {
        return l2dao.getCurrentPage(book);
    }


    public void updateCurrentPage(String book, int page) {

        Runnable runnable = () -> {
            db.level2_booksDao().updateCurrentPage(book, page);
        };
        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.submit(runnable);

    }


    public LiveData<Level1_Books> getBook(String book) {
        return l2dao.getBook(book);
    }

  
    public LiveData<List<Level2_Pages>> getPages(String book) {
        return l2PDao.getPages(book);
    }

    public LiveData<Integer> getPageNumberOfVerse(String book, String chapter,String verse) {
        return l2PDao.getPageNumberOfVerse(book, chapter,verse);
    }


    public LiveData<List<String>> getChapters(String book) {
        return l2PDao.getChapters(book);
    }

    public LiveData<List<String>> getVerses(String book, String chapter){
        return l2PDao.getVerses(book,chapter);
    }
    public LiveData<List<Level2_Pages>> getMatchedL2Pages(String key){
        return l2PDao.getMatchedL2Pages(key);
    }
    public LiveData<List<Level2_Pages>> getNavVerses(String book, String chapter){
        return l2PDao.getNavVerses(book,chapter);
    }


    public LiveData<Level2_Pages> getL2Pages(String bookName, int page) {
        return  l2PDao.getL2Page(bookName,page);
    }
}
