package com.example.book.dao.tags;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tags")
public class Tags {

    @PrimaryKey(autoGenerate = true)
    int id;
    @NonNull
    String tagName;
    @NonNull
    String bookName;
    @NonNull
    int level;
    @NonNull
    String lastLevel;
    @NonNull
    int pageNumber;

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
        this.bookName = bookName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLastLevel() {
        return lastLevel;
    }

    public void setLastLevel(String lastLevel) {
        this.lastLevel = lastLevel;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public String toString() {
        return "Tags{" +

                ", tagName='" + tagName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", level=" + level +
                ", lastLevel='" + lastLevel + '\'' +
                ", pageNumber=" + pageNumber +
                '}';
    }

    public Tags(String tagName, String bookName, int level, String lastLevel, int pageNumber) {
        this.tagName = tagName;
        this.bookName = bookName;
        this.level = level;
        this.lastLevel = lastLevel;
        this.pageNumber = pageNumber;
    }
}
