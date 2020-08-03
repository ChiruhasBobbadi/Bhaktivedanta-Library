package com.example.book.dao.bookmarks;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookmarksDao {

    //@Query("insert into bookmarks ")

    @Query("SELECT EXISTS(SELECT * FROM bookmarks WHERE (bookName=:bookName and canto=:canto) and (chapter=:chapter and verseName=:verseName))")
    LiveData<Boolean> checkBookmark(String bookName, String canto, String chapter, String verseName);

    @Insert
    void addBookmark(Bookmarks bookmarks);

    @Query("delete from bookmarks where (bookName=:bookName and canto=:canto) and (chapter=:chapter and verseName=:verseName)")
    void removeBookmark(String bookName,String canto,String chapter,String verseName);

    @Query("select * from bookmarks")
    LiveData<List<Bookmarks>> getAll();

}
