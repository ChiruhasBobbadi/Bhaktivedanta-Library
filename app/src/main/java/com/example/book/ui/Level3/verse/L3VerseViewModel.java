package com.example.book.ui.Level3.verse;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.book.database.L2Repo;
import com.example.book.database.L3Repo;

import java.util.List;

public class L3VerseViewModel extends AndroidViewModel {
    L3Repo repo;
    private static final String TAG = "L3VerseViewModel";
    public L3VerseViewModel(@NonNull Application application) {
        super(application);

        repo = new L3Repo(application);

    }
    //todo
    public LiveData<List<Integer>> getVerses(String book,String canto, String chapter) {
        Log.d(TAG, "book: "+book);
        Log.d(TAG, "canto: "+canto );
        Log.d(TAG, "chapter: "+chapter);
        return repo.getVerses(book,canto,chapter);
    }

    public LiveData<Integer> getPageNumberOfVerse(String book,String canto, String chapter,String verse) {
        return repo.getPageNumberOfVerse(book,canto,chapter,verse);
    }
}
