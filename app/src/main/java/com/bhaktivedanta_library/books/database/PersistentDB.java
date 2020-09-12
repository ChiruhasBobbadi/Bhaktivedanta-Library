package com.bhaktivedanta_library.books.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bhaktivedanta_library.books.dao.bookmarks.Bookmarks;
import com.bhaktivedanta_library.books.dao.bookmarks.BookmarksDao;
import com.bhaktivedanta_library.books.dao.tags.Tags;
import com.bhaktivedanta_library.books.dao.tags.TagsDao;

@androidx.room.Database(entities = {
        Bookmarks.class, Tags.class
}, version = 2)
public abstract class PersistentDB extends RoomDatabase {
    private static PersistentDB instance;

    public static synchronized PersistentDB getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, PersistentDB.class, "persistentDB.db")
                    .fallbackToDestructiveMigration()
                    .build();


        return instance;
    }


    public abstract BookmarksDao bookmarksDao();

    public abstract TagsDao tagsDao();
}
