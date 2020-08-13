package com.bhaktivedanta_library.books.dao.bookmarks;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bhaktivedanta_library.books.database.BookMarksRepo;

public class BookmarksViewModel extends AndroidViewModel {
    BookMarksRepo repo;
    private static final String TAG = "BookmarksViewModel";
    public BookmarksViewModel(@NonNull Application application) {
        super(application);

        repo = new BookMarksRepo(application);

    }

    public LiveData<Boolean> isBookmark(String bookName,String canto,String chapter,String verseName){

           return repo.isBookmark(bookName,canto,chapter,verseName);

    }

    public void addBookmark(Bookmarks bookmarks){
        repo.addBookmark(bookmarks);
    }

    public void removeBookmark(Bookmarks bookmarks){
        Log.d(TAG, "removeBookmark: "+bookmarks.toString());
        repo.removeBookmark(bookmarks);
    }
}
