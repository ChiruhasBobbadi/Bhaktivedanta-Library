package com.bhaktivedanta_library.books.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bhaktivedanta_library.books.dao.level1.books.Level1_Books;
import com.bhaktivedanta_library.books.dao.level1.books.Level1_BooksDao;
import com.bhaktivedanta_library.books.dao.level1.pages.Level1_Pages;
import com.bhaktivedanta_library.books.dao.level1.pages.Level1_PagesDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class L1Repo implements Level1_BooksDao, Level1_PagesDao {
    Database db;
    private Level1_BooksDao l1dao;
    private Level1_PagesDao l1PDao;

    public L1Repo(Application application) {

        db = Database.getInstance(application);

        l1dao = db.level1_booksDao();
        l1PDao = db.level1_pagesDao();
    }

    @Override
    public LiveData<Integer> getCurrentPage(String book) {
        return l1dao.getCurrentPage(book);
    }

    @Override
    public void updateCurrentPage(String book, int page) {

        Runnable runnable = () -> {
            db.level1_booksDao().updateCurrentPage(book, page);
        };
        ExecutorService ex = Executors.newSingleThreadExecutor();
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

    public LiveData<Integer> getPageNumberOfChapter(String book, String chapter) {
        return l1PDao.getPageNumberOfChapter(book, chapter);
    }


    public LiveData<List<Level1_Pages>> getChapters(String book) {
        return l1PDao.getChapters(book);
    }


    public LiveData<List<Level1_Pages>> getMatchedL1Pages(String key){
        return l1PDao.getMatchedL1Pages(key);
    }


   public LiveData<List<String>> getChapterNames(String book){
        return l1PDao.getChapterNames(book);
    }
}
