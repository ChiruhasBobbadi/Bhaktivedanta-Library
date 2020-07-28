package com.example.book.dao.level1.pages;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface Level1_PagesDao {
    @Insert
    void insert(Level1_Pages page);

    @Query("SELECT level from lookup_table where bookName=:bookName")
    int getLevel(String bookName);
}
