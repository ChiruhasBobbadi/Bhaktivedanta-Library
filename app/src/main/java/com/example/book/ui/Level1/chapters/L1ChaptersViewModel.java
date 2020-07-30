package com.example.book.ui.Level1.chapters;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.book.database.L1BookRepo;

import java.util.List;

public class L1ChaptersViewModel extends AndroidViewModel {


    L1BookRepo repo;

    public L1ChaptersViewModel(@NonNull Application application) {
        super(application);

        repo = new L1BookRepo(application);

    }

    public LiveData<List<String>> getChapters(String book) {
        return repo.getChapters(book);
    }


}
