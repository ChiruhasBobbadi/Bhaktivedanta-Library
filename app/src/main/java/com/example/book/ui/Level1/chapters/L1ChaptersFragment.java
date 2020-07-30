package com.example.book.ui.Level1.chapters;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.book.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class L1ChaptersFragment extends Fragment {


    private static final String TAG = "L1ChaptersFragment";
    SharedPreferences sharedpreferences;
    String bookName;
    L1ChaptersViewModel viewModel;
    List<String> chapters;
    ListView listView;

    public L1ChaptersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_l1_chapters, container, false);

        sharedpreferences = this.getActivity().getSharedPreferences("dataStore",
                MODE_PRIVATE);
        bookName = sharedpreferences.getString("bookName", "");

        Log.d(TAG, "onCreateView: " + bookName);

        viewModel = ViewModelProviders.of(this).get(L1ChaptersViewModel.class);

        viewModel.getChapters(bookName).observe(getViewLifecycleOwner(), strings -> {
            chapters = strings;

            /*List<String> temp = new ArrayList<>();

            for (int i = 0; i < chapters.size(); i++) {
                temp.add((i + 1) + ". " + chapters.get(i));
            }
            chapters = temp;*/
            createListView(chapters);

        });


        listView = root.findViewById(R.id.list);
        return root;
    }

    public void createListView(List<String> list) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.chapter_view, list);
        listView.setAdapter(arrayAdapter);
    }
}