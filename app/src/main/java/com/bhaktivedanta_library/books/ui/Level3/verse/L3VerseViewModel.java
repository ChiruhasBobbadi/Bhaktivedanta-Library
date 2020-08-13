package com.bhaktivedanta_library.books.ui.Level3.verse;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bhaktivedanta_library.books.dao.level3.pages.Level3_Pages;
import com.bhaktivedanta_library.books.database.L3Repo;

import java.util.List;

public class L3VerseViewModel extends AndroidViewModel {
    L3Repo repo;
    private static final String TAG = "L3VerseViewModel";
    public L3VerseViewModel(@NonNull Application application) {
        super(application);

        repo = new L3Repo(application);

    }

    public LiveData<List<String>> getVerses(String book,String canto, String chapter) {

        return repo.getVerses(book,canto,chapter);
    }

    public LiveData<Integer> getPageNumberOfVerse(String book,String canto, String chapter,String verse) {
        return repo.getPageNumberOfVerse(book,canto,chapter,verse);
    }
    public LiveData<List<Level3_Pages>> getNavVerses(String book, String canto, String chapter){
        return repo.getNavVerses(book,canto,chapter);
    }
}
