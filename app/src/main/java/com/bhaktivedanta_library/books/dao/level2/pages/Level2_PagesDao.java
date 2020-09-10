package com.bhaktivedanta_library.books.dao.level2.pages;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Level2_PagesDao {
    @Query("select * from level2_pages where bookName=:book order by pageNumber")
    LiveData<List<Level2_Pages>> getPages(String book);

    @Query("select DISTINCT chapterName from level2_pages where bookName=:book ORDER by chapter")
    LiveData<List<String>> getChapters(String book);

    @Query("select pageNumber from level2_pages WHERE (bookName=:book and chapterName=:chapter)  and  verseName=:verse")
    LiveData<Integer> getPageNumberOfVerse(String book,String chapter,String verse);

    @Query("select * from level2_pages where bookName=:book and chapterName=:chapter ORDER by verse")
    LiveData<List<Level2_Pages>> getNavVerses(String book, String chapter);

    @Query("select verseName from level2_pages where bookName=:book and chapterName=:chapter ORDER by verse")
    LiveData<List<String>> getVerses(String book, String chapter);


    @Query("SELECT * FROM level2_pages WHERE translation LIKE '%' || :key || '%' or purport LIKE '%' || :key || '%' ")
    LiveData<List<Level2_Pages>> getMatchedL2Pages(String key);

    @Query("SELECT * FROM level2_pages WHERE bookName=:bookName and pageNumber=:page")
    LiveData<Level2_Pages> getL2Page(String bookName, int page);
}
