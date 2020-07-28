package com.example.book.dao.level2.books;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface Level2_BooksDao {
    @Insert
    void insert(Level2_Books books);

    @Query("SELECT level from lookup_table where bookName=:bookName")
    int getLevel(String bookName);
}
