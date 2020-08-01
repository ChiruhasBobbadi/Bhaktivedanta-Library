package com.example.book.dao.level3.books;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.book.dao.level1.books.Level1_Books;

@Dao
public interface Level3_BooksDao {
    @Query("select currentPage from level3_books where bookName=:book")
    LiveData<Integer> getCurrentPage(String book);

    @Query("UPDATE level3_books SET currentPage =:page WHERE bookName =:book")
    void updateCurrentPage(String book,int page);

    @Query("select * from level3_books where bookName=:book")
    LiveData<Level3_Books> getBook(String book);
}
