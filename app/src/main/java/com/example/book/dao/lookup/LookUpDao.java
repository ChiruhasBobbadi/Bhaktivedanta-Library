package com.example.book.dao.lookup;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LookUpDao {


    @Query("SELECT * FROM lookup")
    LiveData<List<LookupTable>> getAll();

    @Query("SELECT bookName FROM lookup")
    LiveData<List<String>> getBooks();

    @Query("SELECT level from lookup where bookName=:book")
    LiveData<Integer> getLevel(String book);

}
