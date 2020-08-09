package com.example.book.dao.tags;

import android.app.Application;
import android.nfc.Tag;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class TagsViewModel extends AndroidViewModel {
    TagsRepo repo;
    public TagsViewModel(@NonNull Application application) {
        super(application);
        repo = new TagsRepo(application);
    }


    public LiveData<Boolean> checkTag(String tagName,String bookName,String lastLevel){

        return repo.checkTag(tagName,bookName,lastLevel);
    }

    public void addTag(Tags tag){
        repo.addTag(tag);
    }

    public void deleteTag(String tagName,String bookName,String lastLevel){

        repo.deleteTag(tagName,bookName,lastLevel);
    }
    public LiveData<List<String>> getTagsOfPage(String bookName, String lastLevel,int page ){
        return repo.getTagsOfPage(bookName,lastLevel,page);
    }

    public LiveData<List<Tags>> getAllTag(){
        return repo.getAll();
    }

    public LiveData<List<Tags>> getTagsByName(String tagName){
        return repo.getTagsByName(tagName);
    }
}
