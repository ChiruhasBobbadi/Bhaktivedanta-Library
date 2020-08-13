package com.bhaktivedanta_library.books.ui.Level2.chapters;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bhaktivedanta_library.books.database.L2Repo;

import java.util.List;

public class L2ChaptersViewModel extends AndroidViewModel {
    L2Repo repo;

    public L2ChaptersViewModel(@NonNull Application application) {
        super(application);

        repo = new L2Repo(application);

    }

    public LiveData<List<String>> getChapters(String book) {
        return repo.getChapters(book);
    }


}
