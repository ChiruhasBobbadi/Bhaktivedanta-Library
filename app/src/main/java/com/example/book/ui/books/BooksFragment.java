package com.example.book.ui.books;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book.R;

import java.util.ArrayList;
import java.util.List;

public class BooksFragment extends Fragment {

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
        /*booksViewModel =
                ViewModelProviders.of(this).get(BooksViewModel.class);
        final TextView textView = root.findViewById(R.id.text_home);
        booksViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
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