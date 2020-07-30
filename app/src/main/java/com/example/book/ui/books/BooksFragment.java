package com.example.book.ui.books;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book.MainActivity;
import com.example.book.R;
import com.example.book.dao.lookup.LookupTable;
import com.example.book.database.L1BookRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooksFragment extends Fragment {
    private static final String TAG = "BooksFragment";

    private BooksViewModel booksViewModel;
    RecyclerView rv;
    List<String> books;
    GridAdapter adapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        init();
        View root = inflater.inflate(R.layout.fragment_books, container, false);

        rv = root.findViewById(R.id.rv);

        adapter= new GridAdapter(books);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);

        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(adapter);
        booksViewModel =
                ViewModelProviders.of(getActivity()).get(BooksViewModel.class);


        booksViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<LookupTable>>() {
            @Override
            public void onChanged(List<LookupTable> lookupTables) {

                adapter.setData(lookupTables);
            }
        });





        return root;
    }

    private void init() {
        books = new ArrayList<>();
        books.add("KṚṢṆA, The Supreme Personality of Godhead");
        books.add("The Nectar of Instruction");
        books.add("Śrī Īśopaniṣad");
        books.add("Kṛṣṇa Consciousness, The Topmost Yoga System");

        books.add("Rāja-Vidyā: The King of Knowledge");
        books.add("The Nectar of Devotion");
        books.add("Teachings of Lord Caitanya");
        books.add("Elevation to Kṛṣṇa Consciousness");



    }
}