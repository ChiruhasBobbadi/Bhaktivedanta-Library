package com.bhaktivedanta_library.books.dao.level1.pages;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "level1_pages")
public class Level1_Pages {
    @PrimaryKey
    private int id;
    private int chapter;
    private int pageNumber;
    @NonNull
    private String bookName;
    @NonNull
    private String chapterName;
    @NonNull
    private String purport;
    private String synonyms;
    private String text;
    private String translation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getPurport() {
        return purport;
    }

    public void setPurport(String purport) {
        this.purport = purport;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Level1_Pages(int id, int chapter, int pageNumber, String bookName, String chapterName, String purport, String synonyms, String text, String translation) {
        this.id = id;
        this.chapter = chapter;
        this.pageNumber = pageNumber;
        this.bookName = bookName;
        this.chapterName = chapterName;
        this.purport = purport;
        this.synonyms = synonyms;
        this.text = text;
        this.translation = translation;
    }
}
