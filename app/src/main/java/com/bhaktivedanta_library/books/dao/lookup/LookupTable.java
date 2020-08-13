package com.bhaktivedanta_library.books.dao.lookup;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lookup")
public class LookupTable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private int level;
    @NonNull
    private String bookName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LookupTable(int level, String bookName) {
        this.level = level;
        this.bookName = bookName;
    }
}
