package com.example.book.dao.lookup;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lookup_table")
public class LookupTable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String bookName;
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LookupTable(int id, String bookName, int level) {
        this.id = id;
        this.bookName = bookName;
        this.level = level;
    }

    public LookupTable() {
    }

    public String getBookName() {
        return bookName;
    }

    public int getLevel() {
        return level;
    }



}
