package com.example.book.dao.tags;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tags")
public class Tags {
    @PrimaryKey(autoGenerate = true)
    int id;
    String tagName;
    String bookName;
    int level;
    String cantoName;
    String chapterName;
    String verseName;
    int pageNumber;

    public Tags(String tagName, String bookName, int level, String cantoName, String chapterName, String verseName, int pageNumber) {
        this.tagName = tagName;
        this.bookName = bookName;
        this.level = level;
        this.cantoName = cantoName;
        this.chapterName = chapterName;
        this.verseName = verseName;
        this.pageNumber = pageNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        bookName = bookName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCantoName() {
        return cantoName;
    }

    public void setCantoName(String cantoName) {
        this.cantoName = cantoName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getVerseName() {
        return verseName;
    }

    public void setVerseName(String verseName) {
        this.verseName = verseName;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
