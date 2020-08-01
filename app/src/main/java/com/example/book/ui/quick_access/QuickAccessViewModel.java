package com.example.book.ui.quick_access;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.book.dao.level1.books.Level1_Books;
import com.example.book.database.L1Repo;
import com.example.book.database.L2Repo;
import com.example.book.database.L3Repo;
import com.example.book.database.LookUpRepo;

import java.util.List;

public class QuickAccessViewModel extends AndroidViewModel {
    LookUpRepo lRepo;
    L1Repo l1Repo;
    L2Repo l2Repo;
    L3Repo l3Repo;

    public QuickAccessViewModel(@NonNull Application application) {
        super(application);
        lRepo = new LookUpRepo(application);
        l1Repo = new L1Repo(application);
        l2Repo = new L2Repo(application);
        l3Repo = new L3Repo(application);
    }

   public LiveData<List<String>> getBooks(){
      return   lRepo.getBooks();
    }

    public LiveData<Integer> getLevel(String book){
        return  lRepo.getLevel(book);
    }

    public LiveData<List<String>> getL1Chapters(String book){

       return l1Repo.getChapters(book);



    }


}
