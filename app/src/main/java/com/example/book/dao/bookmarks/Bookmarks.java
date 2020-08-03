package com.example.book.dao.bookmarks;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmarks")
public class Bookmarks {

    @PrimaryKey(autoGenerate = true)
    int id;

    String canto;
    String chapter;
    int level;
    int pageNumber;
    String verseName;
    String bookName;

    @Override
    public String toString() {
        return "Bookmarks{" +
                "canto='" + canto + '\'' +
                ", chapter='" + chapter + '\'' +
                ", level=" + level +
                ", pageNumber=" + pageNumber +
                ", verseName='" + verseName + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCanto() {
        return canto;
    }

    public void setCanto(String canto) {
        this.canto = canto;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getVerseName() {
        return verseName;
    }

    public void setVerseName(String verseName) {
        this.verseName = verseName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Bookmarks(String canto, String chapter, int level, int pageNumber, String verseName, String bookName) {
        this.canto = canto;
        this.chapter = chapter;
        this.level = level;
        this.pageNumber = pageNumber;
        this.verseName = verseName;
        this.bookName = bookName;
    }
}
