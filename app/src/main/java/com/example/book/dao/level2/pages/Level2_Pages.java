package com.example.book.dao.level2.pages;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "level2_pages")
public class Level2_Pages {
    @PrimaryKey(autoGenerate = true)
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

    public int getId() {
        return id;
    }

    public int getChapter() {
        return chapter;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public String getBookName() {
        return bookName;
    }

    public String getVerse() {
        return verse;
    }

    public String getChapterName() {
        return chapterName;
    }

    public String getPurport() {
        return purport;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public String getText() {
        return text;
    }

    public String getTranslation() {
        return translation;
    }

    public Level2_Pages(int id) {
        this.id = id;
    }

    public Level2_Pages(int id, int chapter, int pageNumber, String bookName, String verse, String chapterName, String purport, String synonyms, String text, String translation) {
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
    }
}
