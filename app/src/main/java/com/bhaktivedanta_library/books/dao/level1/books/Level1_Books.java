package com.bhaktivedanta_library.books.dao.level1.books;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "level1_books")
public class Level1_Books {
    @PrimaryKey
    private int id;
    @NonNull
    private int chapterCount;
    @NonNull
    private int pagesCount;
    @NonNull
    private int currentPage;
    @NonNull
    private String bookName;

    String dedication, foreword, introduction, introductoryNote, invocation, mission, preface, prologue;

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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public String getIntroductoryNote() {
        return introductoryNote;
    }

    public void setIntroductoryNote(String introductoryNote) {
        this.introductoryNote = introductoryNote;
    }

    public String getInvocation() {
        return invocation;
    }

    public void setInvocation(String invocation) {
        this.invocation = invocation;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getPreface() {
        return preface;
    }

    public void setPreface(String preface) {
        this.preface = preface;
    }

    public String getPrologue() {
        return prologue;
    }

    public void setPrologue(String prologue) {
        this.prologue = prologue;
    }

    public Level1_Books(int id, int chapterCount, int pagesCount, int currentPage, String bookName, String dedication, String foreword, String introduction, String introductoryNote, String invocation, String mission, String preface, String prologue) {
        this.id = id;
        this.chapterCount = chapterCount;
        this.pagesCount = pagesCount;
        this.currentPage = currentPage;
        this.bookName = bookName;
        this.dedication = dedication;
        this.foreword = foreword;
        this.introduction = introduction;
        this.introductoryNote = introductoryNote;
        this.invocation = invocation;
        this.mission = mission;
        this.preface = preface;
        this.prologue = prologue;
    }
}
