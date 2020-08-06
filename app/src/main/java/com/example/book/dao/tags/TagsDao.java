package com.example.book.dao.tags;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TagsDao {

    @Insert
     void addTag(Tags tag);
    //todo
    @Delete
     void deleteTag(Tags tag);

    @Query("select * from tags where tagName LIKE '%' || :tag || '%'")
    List<Tags> getTags(String tag);



}
