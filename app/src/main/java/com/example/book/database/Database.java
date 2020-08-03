package com.example.book.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.book.dao.bookmarks.Bookmarks;
import com.example.book.dao.bookmarks.BookmarksDao;
import com.example.book.dao.level1.books.Level1_Books;
import com.example.book.dao.level1.books.Level1_BooksDao;
import com.example.book.dao.level1.pages.Level1_Pages;
import com.example.book.dao.level1.pages.Level1_PagesDao;
import com.example.book.dao.level2.books.Level2_Books;
import com.example.book.dao.level2.books.Level2_BooksDao;
import com.example.book.dao.level2.pages.Level2_Pages;
import com.example.book.dao.level2.pages.Level2_PagesDao;
import com.example.book.dao.level3.books.Level3_Books;
import com.example.book.dao.level3.books.Level3_BooksDao;
import com.example.book.dao.level3.pages.Level3_Pages;
import com.example.book.dao.level3.pages.Level3_PagesDao;
import com.example.book.dao.lookup.LookUpDao;
import com.example.book.dao.lookup.LookupTable;
import com.example.book.dao.tags.Tags;
import com.example.book.dao.tags.TagsDao;


@androidx.room.Database
        (entities = {LookupTable.class,
                Level1_Books.class, Level1_Pages.class,
                Level2_Books.class, Level2_Pages.class,
                Level3_Books.class, Level3_Pages.class,
                Bookmarks.class, Tags.class
        }, version = 6)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public static synchronized Database getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, Database.class, "bookDatabase.db")
                    .createFromAsset("bookDatabase.db")
                    .fallbackToDestructiveMigration()
                    .build();


        return instance;
    }

    public abstract LookUpDao lookUpDao();

    public abstract Level1_BooksDao level1_booksDao();

    public abstract Level1_PagesDao level1_pagesDao();

    public abstract Level2_BooksDao level2_booksDao();

    public abstract Level2_PagesDao level2_pagesDao();

    public abstract Level3_BooksDao level3_booksDao();

    public abstract Level3_PagesDao level3_pagesDao();

    public abstract BookmarksDao bookmarksDao();

    public abstract TagsDao tagsDao();
}
