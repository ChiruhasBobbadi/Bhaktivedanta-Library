package com.bhaktivedanta_library.books.ui.Level3.Chapters;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bhaktivedanta_library.books.database.L3Repo;

import java.util.List;

public class L3ChapterViewModel extends AndroidViewModel {
    L3Repo repo;
    private static final String TAG = "L3ChapterViewModel";
    public L3ChapterViewModel(@NonNull Application application) {
        super(application);

        repo = new L3Repo(application);

    }

    public LiveData<List<String>> getChapters(String book, String canto) {
        Log.d(TAG, "book: "+book);
        Log.d(TAG, "canto: "+canto);
        return repo.getChapters(book, canto);
    }
}

