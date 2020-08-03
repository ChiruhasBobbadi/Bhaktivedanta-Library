package com.example.book.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.book.dao.bookmarks.Bookmarks;
import com.example.book.dao.bookmarks.BookmarksDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookMarksRepo {
    Database db;
    private BookmarksDao bookmarksDao;

    public BookMarksRepo(Application application) {

        db = Database.getInstance(application);

        bookmarksDao = db.bookmarksDao();
    }

    public LiveData<Boolean> isBookmark(String bookName, String canto, String chapter, String verseName) {

        return bookmarksDao.checkBookmark(bookName, canto, chapter, verseName);
    }

    public void addBookmark(Bookmarks bookmarks) {
        Runnable runnable = () -> {
            db.bookmarksDao().addBookmark(bookmarks);
        };
        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.submit(runnable);

    }

    public void removeBookmark(Bookmarks bookmarks) {
        Runnable runnable = () -> {
            db.bookmarksDao().removeBookmark(bookmarks.getBookName(), bookmarks.getCanto(), bookmarks.getChapter(), bookmarks.getVerseName());
        };
        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.submit(runnable);


    }

    public LiveData<List<Bookmarks>> getAll() {
        return bookmarksDao.getAll();
    }


}
