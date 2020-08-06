package com.example.book.dao.level3.pages;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.book.dao.level2.pages.Level2_Pages;

import java.util.List;

@Dao
public interface Level3_PagesDao {
    @Query("select * from level3_pages where bookName=:book order by pageNumber")
    LiveData<List<Level3_Pages>> getPages(String book);

    @Query("select DISTINCT chapterName from level3_pages where bookName=:book and level_3_Name=:canto ORDER by chapter")
    LiveData<List<String>> getChapters(String book,String canto);

    @Query("select DISTINCT level_3_Name from level3_pages where bookName=:book ORDER by level_3")
    LiveData<List<String>> getCantos(String book);

    @Query("select pageNumber from level3_pages WHERE (bookName=:book and level_3_Name=:canto)  and (chapterName=:chapter and verseName=:verse) ")
    LiveData<Integer> getPageNumberOfVerse(String book,String canto,String chapter,String verse);

    @Query("select verseName from level3_pages where (bookName=:book and level_3_Name=:canto) and chapterName=:chapter ORDER by verse")
    LiveData<List<String>> getVerses(String book,String canto, String chapter);


    @Query("SELECT * FROM level3_pages WHERE translation LIKE '%' || :key || '%' or  purport LIKE '%' || :key || '%' ")
    LiveData<List<Level3_Pages>> getMatchedL3Pages(String key);
}
