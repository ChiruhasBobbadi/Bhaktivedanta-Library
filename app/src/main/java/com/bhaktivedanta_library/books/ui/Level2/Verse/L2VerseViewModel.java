package com.bhaktivedanta_library.books.ui.Level2.Verse;

import android.app.Application;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bhaktivedanta_library.books.dao.level2.pages.Level2_Pages;
import com.bhaktivedanta_library.books.database.L2Repo;

import java.util.List;

public class L2VerseViewModel extends AndroidViewModel {
    L2Repo repo;

    public L2VerseViewModel(@NonNull Application application) {
        super(application);

        repo = new L2Repo(application);

    }



    public LiveData<Integer> getPageNumberOfVerse(String book, String chapter,String verse) {
        return repo.getPageNumberOfVerse(book, chapter,verse);
    }
    public LiveData<List<Level2_Pages>> getNavVerses(String book, String chapter){
        return repo.getNavVerses(book,chapter);
    }

    public LiveData<Level2_Pages> getL2Page(String bookName,int page){
        return repo.getL2Pages(bookName,page);
    }


}
