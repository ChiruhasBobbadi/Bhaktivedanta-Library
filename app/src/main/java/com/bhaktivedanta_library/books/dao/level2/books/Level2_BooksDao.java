package com.bhaktivedanta_library.books.dao.level2.books;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.bhaktivedanta_library.books.dao.level1.books.Level1_Books;

@Dao
public interface Level2_BooksDao {

    @Query("select currentPage from level2_books where bookName=:book")
    LiveData<Integer> getCurrentPage(String book);

    @Query("UPDATE level2_books SET currentPage = :page WHERE bookName =:book")
    void updateCurrentPage(String book,int page);

    @Query("select * from level2_books where bookName=:book")
    LiveData<Level1_Books> getBook(String book);
}
