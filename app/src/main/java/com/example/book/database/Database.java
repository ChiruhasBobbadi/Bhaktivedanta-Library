package com.example.book.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.book.dao.level1.books.Level1_BooksDao;
import com.example.book.dao.lookup.LookUpDao;
import com.example.book.dao.lookup.LookupTable;


@androidx.room.Database(entities = {LookupTable.class}, version = 1)
abstract class Database extends RoomDatabase {

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

//    public abstract Level1_PagesDao level1_pagesDao();
//
//    public abstract Level2_BooksDao level2_booksDao();
//
//    public abstract Level2_PagesDao level2_pagesDao();
//
//    public abstract Level3_BooksDao level3_booksDao();
//
//    public abstract Level3_PagesDao level3_pagesDao();
}
