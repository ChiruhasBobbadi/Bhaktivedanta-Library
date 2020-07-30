package com.example.book.dao.level2.pages;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "level2_pages")
public class Level2_Pages {
    @PrimaryKey
    private int id;
    private int chapter;
    private int pageNumber;
    private String bookName;
    private int verse;
    private String chapterName;
    private String purport;
    private String synonyms;
    private String text;
    private String translation;
    private String verseName;

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

    public int getVerse() {
        return verse;
    }

    public void setVerse(int verse) {
        this.verse = verse;
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

    public String getVerseName() {
        return verseName;
    }

    public void setVerseName(String verseName) {
        this.verseName = verseName;
    }

    public Level2_Pages(int id, int chapter, int pageNumber, String bookName, int verse, String chapterName, String purport, String synonyms, String text, String translation, String verseName) {
        this.id = id;
        this.chapter = chapter;
        this.pageNumber = pageNumber;
        this.bookName = bookName;
        this.verse = verse;
        this.chapterName = chapterName;
        this.purport = purport;
        this.synonyms = synonyms;
        this.text = text;
        this.translation = translation;
        this.verseName = verseName;
    }
}
