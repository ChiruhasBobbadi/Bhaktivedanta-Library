package com.example.book.ui.Level1.chapters;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.book.dao.level1.pages.Level1_Pages;
import com.example.book.database.L1Repo;

import java.util.List;

public class L1ChaptersViewModel extends AndroidViewModel {


    L1Repo repo;

    public L1ChaptersViewModel(@NonNull Application application) {
        super(application);

        repo = new L1Repo(application);

    }

    public LiveData<List<Level1_Pages>> getChapters(String book) {
        return repo.getChapters(book);
    }

    public LiveData<Integer> getPageNumberOfChapter(String book,String chapter) {
        return repo.getPageNumberOfChapter(book,chapter);
    }


}
