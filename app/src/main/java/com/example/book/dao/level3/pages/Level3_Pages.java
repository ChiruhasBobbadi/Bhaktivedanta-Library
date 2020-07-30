package com.example.book.dao.level3.pages;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "level3_pages")
public class Level3_Pages {
    @PrimaryKey
    private int id;
    private int chapter;
    private int pageNumber;
    private String bookName;
    private String verse;
    private String chapterName;
    private String purport;
    private String synonyms;
    private String text;
    private String translation;
    private int level_3;
    private String level_3_Name;

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

    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
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

    public int getLevel_3() {
        return level_3;
    }

    public void setLevel_3(int level_3) {
        this.level_3 = level_3;
    }

    public String getLevel_3_Name() {
        return level_3_Name;
    }

    public void setLevel_3_Name(String level_3_Name) {
        this.level_3_Name = level_3_Name;
    }

    public Level3_Pages(int id, int chapter, int pageNumber, String bookName, String verse, String chapterName, String purport, String synonyms, String text, String translation, int level_3, String level_3_Name) {
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
        this.level_3 = level_3;
        this.level_3_Name = level_3_Name;
    }
}
