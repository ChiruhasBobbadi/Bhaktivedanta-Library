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

    private Map<String,String> map = new HashMap<>();

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

                Log.d(TAG, "onChanged: "+lookupTables.size());
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

        map.put("KṚṢṆA, The Supreme Personality of Godhead","a");
        map.put("The Nectar of Instruction","b");
        map.put("Śrī Īśopaniṣad","c");
        map.put("Kṛṣṇa Consciousness, The Topmost Yoga System","d");
        map.put("Rāja-Vidyā: The King of Knowledge","e");
        map.put("The Nectar of Devotion","f");
        map.put("Teachings of Lord Caitanya","g");
        map.put("Elevation to Kṛṣṇa Consciousness","h");
        map.put("Kṛṣṇa Consciousness, The Matchless Gift","i");
        map.put("Transcendental Teachings of Prahlāda Mahārāja","j");
        map.put("Teachings of Lord Kapila, the Son of Devahuti","k");
        map.put("Teachings of Queen Kuntī","l");
        map.put("The Path of Perfection","m");
        map.put("The Perfection of Yoga","n");
        map.put("Beyond Birth & Death","o");
        map.put("On the Way to Kṛṣṇa","p");
        map.put("Easy Journey to Other Planets","q");
        map.put("Perfect Questions, Perfect Answers","r");
        map.put("Kṛṣṇa, the Reservoir of Pleasure","s");
        map.put("Bhagavad-gītā As It Is","t");
        map.put("Life Comes from Life","u");
        map.put("The Science of Self Realization","v");

    }
}