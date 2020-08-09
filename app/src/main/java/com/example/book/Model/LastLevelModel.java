package com.example.book.Model;

public class LastLevelModel {
    String lastLevelName;
    String translation;

    public String getLastLevelName() {
        return lastLevelName;
    }

    public void setLastLevelName(String lastLevelName) {
        this.lastLevelName = lastLevelName;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public LastLevelModel(String lastLevelName, String translation) {
        this.lastLevelName = lastLevelName;
        this.translation = translation;
    }
}
