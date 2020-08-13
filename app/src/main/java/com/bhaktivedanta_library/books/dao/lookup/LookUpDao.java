package com.bhaktivedanta_library.books.dao.lookup;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LookUpDao {


    @Query("SELECT * FROM lookup order by bookName")
    LiveData<List<LookupTable>> getAll();

    @Query("SELECT bookName FROM lookup order by bookName")
    LiveData<List<String>> getBooks();

    @Query("SELECT level from lookup where bookName=:book")
    LiveData<Integer> getLevel(String book);

}
