package com.example.book.ui.Level2.Verse;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.book.database.L2Repo;

import java.util.List;

public class L2VerseViewModel extends AndroidViewModel {
    L2Repo repo;

    public L2VerseViewModel(@NonNull Application application) {
        super(application);

        repo = new L2Repo(application);

    }

    public LiveData<List<String>> getVerses(String book,String chapter) {
        return repo.getVerses(book,chapter);
    }

    public LiveData<Integer> getPageNumberOfVerse(String book, String chapter,String verse) {
        return repo.getPageNumberOfVerse(book, chapter,verse);
    }
}
