package com.example.book.dao.lookup;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface LookUpDao {
    @Insert
    void insert(LookupTable lookup);

    @Update
    void  update(LookupTable lookup);

    @Query("SELECT level from lookup_table where bookName=:bookName")
    int getLevel(String bookName);

}
