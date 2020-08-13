package com.bhaktivedanta_library.books.ui.quick_access;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bhaktivedanta_library.books.database.L1Repo;
import com.bhaktivedanta_library.books.database.L2Repo;
import com.bhaktivedanta_library.books.database.L3Repo;
import com.bhaktivedanta_library.books.database.LookUpRepo;

import java.util.List;

public class QuickAccessViewModel extends AndroidViewModel {
    LookUpRepo lRepo;
   public L1Repo l1Repo;
    public  L2Repo l2Repo;
    public L3Repo l3Repo;

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




}
