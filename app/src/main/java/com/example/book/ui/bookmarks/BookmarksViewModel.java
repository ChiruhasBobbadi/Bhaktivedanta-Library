package com.example.book.ui.bookmarks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.book.dao.bookmarks.Bookmarks;
import com.example.book.database.BookMarksRepo;

import java.util.List;

public class BookmarksViewModel extends AndroidViewModel {

    BookMarksRepo repo;

    public BookmarksViewModel(@NonNull Application application) {
        super(application);
        repo = new BookMarksRepo(application);
    }

    public LiveData<List<Bookmarks>> getAllBookmarks(){
        return repo.getAll();
    }
}