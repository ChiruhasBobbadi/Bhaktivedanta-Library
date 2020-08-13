package com.bhaktivedanta_library.books.dao.tags;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TagsDao {

   /* @Query("Insert into tags(tagName,bookName,level,lastLevel,pageNumber) values(:tagName,:bookName,:level,:lastLevel,:pageNumber)")
    void addTag(String tagName,String bookName,int level,String lastLevel,int pageNumber);*/

   @Insert
   void addTag(Tags tag);

    @Query("delete from tags where (tagName=:tagName and bookName=:bookName) and lastLevel=:lastLevel ")
    void deleteTag(String tagName, String bookName, String lastLevel);

    @Query("select * from tags where tagName LIKE '%' || :tag || '%' group by lastLevel")
    LiveData<List<Tags>> getTagsByName(String tag);

    @Query("SELECT EXISTS (select * from tags where tagName=:tagName and (bookName=:bookName and lastLevel=:lastLevel))")
    LiveData<Boolean> checkTag(String tagName, String bookName, String lastLevel);

    @Query("select tagName from tags where bookName=:bookName and (lastLevel=:lastLevel and pageNumber=:page)")
    LiveData<List<String>> getTagsOfPage(String bookName, String lastLevel,int page);

    @Query("select * from tags")
    LiveData<List<Tags>> getAll();



}
