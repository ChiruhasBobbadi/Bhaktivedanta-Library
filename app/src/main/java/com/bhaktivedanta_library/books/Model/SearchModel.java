package com.bhaktivedanta_library.books.Model;

public class SearchModel {
    int pageNumer;
    String bookName;
    String lastLevelName;
    int level;
    String displayName;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getPageNumer() {
        return pageNumer;
    }

    public void setPageNumer(int pageNumer) {
        this.pageNumer = pageNumer;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getLastLevelName() {
        return lastLevelName;
    }

    public void setLastLevelName(String lastLevelName) {
        this.lastLevelName = lastLevelName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public SearchModel(int pageNumer, String bookName, String lastLevelName, int level, String displayName) {
        this.pageNumer = pageNumer;
        this.bookName = bookName;
        this.lastLevelName = lastLevelName;
        this.level = level;
        this.displayName = displayName;
    }
}
