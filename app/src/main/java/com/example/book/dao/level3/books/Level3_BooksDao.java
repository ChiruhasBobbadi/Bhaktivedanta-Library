package com.example.book.dao.level3.books;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface Level3_BooksDao {
    @Insert
    void insert(Level3_Books books);

    @Query("SELECT level from lookup_table where bookName=:bookName")
    int getLevel(String bookName);
}
