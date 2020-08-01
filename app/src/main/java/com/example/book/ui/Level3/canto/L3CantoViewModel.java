package com.example.book.ui.Level3.canto;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.book.database.L2Repo;
import com.example.book.database.L3Repo;

import java.util.List;

public class L3CantoViewModel extends AndroidViewModel {
    L3Repo repo;

    public L3CantoViewModel(@NonNull Application application) {
        super(application);

        repo = new L3Repo(application);

    }

    public LiveData<List<String>> getCanto(String book) {
        return repo.getCanto(book);
    }

}
