package com.example.book.dao.level1.books;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface Level1_BooksDao {

    @Query("select currentPage from level1_books where bookName=:book")
    LiveData<Integer> getCurrentPage(String book);

    @Query("UPDATE level1_books SET currentPage = :page WHERE bookName =:book")
    void updateCurrentPage(String book,int page);

    @Query("select * from level1_books where bookName=:book")
    LiveData<Level1_Books> getBook(String book);


}



