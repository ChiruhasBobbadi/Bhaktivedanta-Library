package com.example.book.dao.level2.pages;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface Level2_PagesDao {
    @Insert
    void insert(Level2_Pages pages);

    @Query("SELECT level from lookup_table where bookName=:bookName")
    int getLevel(String bookName);

}
