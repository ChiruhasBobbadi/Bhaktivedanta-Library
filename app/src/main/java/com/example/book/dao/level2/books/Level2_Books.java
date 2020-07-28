package com.example.book.dao.level2.books;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "level2_books")
public class Level2_Books {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int chapterCount;
    private int pagesCount;
    private int currentPage;
    private String dedication;
    private String foreword;
    private String introduction;

    private String preface;
    private String bookName;

    public int getId() {
        return id;
    }

    public int getChapterCount() {
        return chapterCount;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public String getDedication() {
        return dedication;
    }

    public String getForeword() {
        return foreword;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getPreface() {
        return preface;
    }

    public String getBookName() {
        return bookName;
    }

    public Level2_Books(int id) {
        this.id = id;
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
