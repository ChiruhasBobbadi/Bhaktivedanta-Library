package com.example.book.dao.level2.books;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "level2_books")
public class Level2_Books {
    @PrimaryKey
    private int id;
    private int chapterCount;
    private int pagesCount;
    private int currentPage;
    private String dedication;
    private String foreword;
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

    public int getChapterCount() {
        return chapterCount;
    }

    public void setChapterCount(int chapterCount) {
        this.chapterCount = chapterCount;
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

    public String getDedication() {
        return dedication;
    }

    public void setDedication(String dedication) {
        this.dedication = dedication;
    }

    public String getForeword() {
        return foreword;
    }

    public void setForeword(String foreword) {
        this.foreword = foreword;
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

    public Level2_Books(int id, int chapterCount, int pagesCount, int currentPage, String dedication, String foreword, String introduction, String preface, String bookName) {
        this.id = id;
        this.chapterCount = chapterCount;
        this.pagesCount = pagesCount;
        this.currentPage = currentPage;
        this.dedication = dedication;
        this.foreword = foreword;
        this.introduction = introduction;
        this.preface = preface;
        this.bookName = bookName;
    }
}
