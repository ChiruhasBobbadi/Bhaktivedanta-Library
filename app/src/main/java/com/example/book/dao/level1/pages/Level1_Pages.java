package com.example.book.dao.level1.pages;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "level1_pages")
public class Level1_Pages {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int chapter;
    private int pageNumber;
    private String bookName;
    private String chapterName;
    private String purport;
    private String synonyms;
    private String text;
    private String translation;

    public Level1_Pages(int id) {
        this.id = id;
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
}
