package com.example.book.ui.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.book.dao.level1.pages.Level1_Pages;
import com.example.book.dao.level2.pages.Level2_Pages;
import com.example.book.dao.level3.pages.Level3_Pages;
import com.example.book.database.L1Repo;
import com.example.book.database.L2Repo;
import com.example.book.database.L3Repo;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {
    L1Repo l1;
    L2Repo l2;
    L3Repo l3;
    public SearchViewModel(@NonNull Application application) {
        super(application);
        l1 = new L1Repo(application);
        l2 = new L2Repo(application);
        l3 = new L3Repo(application);
    }

    public LiveData<List<Level1_Pages>> getMatchedL1Pages(String key){
        return l1.getMatchedL1Pages(key);
    }
    public  LiveData<List<Level2_Pages>> getMatchedL2Pages(String key){
        return l2.getMatchedL2Pages(key);
    }
    public LiveData<List<Level3_Pages>> getMatchedL3Pages(String key){
        return l3.getMatchedL3Pages(key);
    }
}
