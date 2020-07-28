package com.example.book.dao.level3.pages;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface Level3_PagesDao {
    @Insert
    void insert(Level3_Pages pages);

    @Query("SELECT level from lookup_table where bookName=:bookName")
    int getLevel(String bookName);

}
