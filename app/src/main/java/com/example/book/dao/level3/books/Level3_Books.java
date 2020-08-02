package com.example.book.dao.level3.books;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "level3_books")
public class Level3_Books {
    @PrimaryKey
    private int id;
    @NonNull
    private int pagesCount;
    @NonNull
    private int currentPage;

    private String introduction;

    private String preface;
    @NonNull
    private String bookName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPreface() {
        return preface;
    }

    public void setPreface(String preface) {
        this.preface = preface;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Level3_Books(int id, int pagesCount, int currentPage, String introduction, String preface, String bookName) {
        this.id = id;
        this.pagesCount = pagesCount;
        this.currentPage = currentPage;
        this.introduction = introduction;
        this.preface = preface;
        this.bookName = bookName;
    }


}
