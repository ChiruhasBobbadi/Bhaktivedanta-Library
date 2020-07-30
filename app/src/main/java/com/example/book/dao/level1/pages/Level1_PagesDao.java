package com.example.book.dao.level1.pages;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Level1_PagesDao {
    @Query("select * from level1_pages where bookName=:book order by pageNumber")
    LiveData<List<Level1_Pages>> getPages(String book);

    @Query("select chapterName from level1_pages where bookName=:book order by chapter")
    LiveData<List<String>> getChapters(String book);

    //void setBookmark(String book, int val);

}
