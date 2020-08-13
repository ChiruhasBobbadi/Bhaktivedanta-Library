package com.bhaktivedanta_library.books.ui.bookmarks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bhaktivedanta_library.books.dao.bookmarks.Bookmarks;
import com.bhaktivedanta_library.books.database.BookMarksRepo;

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