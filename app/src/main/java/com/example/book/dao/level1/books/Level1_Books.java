package com.example.book.dao.level1.books;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "level1_books")
public class Level1_Books {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int chapterCount;
    private int pagesCount;
    private int currentPage;
    private String dedication ;
    private String foreword ;
    private String introduction ;
    private String introductoryNote ;
    private String invocation ;
    private String mission ;
    private String preface;
    private String prologue;
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

    public String getIntroductoryNote() {
        return introductoryNote;
    }

    public String getInvocation() {
        return invocation;
    }

    public String getMission() {
        return mission;
    }

    public String getPreface() {
        return preface;
    }

    public String getPrologue() {
        return prologue;
    }

    public String getBookName() {
        return bookName;
    }

    public Level1_Books(int id) {
        this.id = id;
    }

    public Level1_Books(int id, int chapterCount, int pagesCount, int currentPage, String dedication, String foreword, String introduction, String introductoryNote, String invocation, String mission, String preface, String prologue, String bookName) {
        this.id = id;
        this.chapterCount = chapterCount;
        this.pagesCount = pagesCount;
        this.currentPage = currentPage;
        this.dedication = dedication;
        this.foreword = foreword;
        this.introduction = introduction;
        this.introductoryNote = introductoryNote;
        this.invocation = invocation;
        this.mission = mission;
        this.preface = preface;
        this.prologue = prologue;
        this.bookName = bookName;
    }
}
