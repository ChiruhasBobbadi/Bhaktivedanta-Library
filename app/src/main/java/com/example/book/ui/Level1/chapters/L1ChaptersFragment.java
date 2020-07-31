package com.example.book.ui.Level1.chapters;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
    View v;

    public L1ChaptersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_l1_chapters, container, false);

        listView = root.findViewById(R.id.list);

        init();

        viewModelCalls();
        listItemClick();

        return root;
    }

    private void listItemClick() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String chap = chapters.get(position);
            v = view;
            viewModel.getPageNumberOfChapter(bookName,chap).observe(getViewLifecycleOwner(), number -> {

                Bundle bundle = new Bundle();
                bundle.putString("pageNumber", number+"-"+"level1");
                bundle.putString("title",bookName);

                NavController controller = Navigation.findNavController(view);

                controller.navigate(R.id.action_l1ChaptersFragment_to_l1Fragment,bundle);

            });


        });
    }

    private void viewModelCalls() {
        viewModel = ViewModelProviders.of(this).get(L1ChaptersViewModel.class);

        viewModel.getChapters(bookName).observe(getViewLifecycleOwner(), strings -> {
            chapters = strings;
            createListView(chapters);

        });
    }

    private void init() {

        sharedpreferences = this.getActivity().getSharedPreferences("dataStore",
                MODE_PRIVATE);
        bookName = sharedpreferences.getString("bookName", "");
    }

    public void createListView(List<String> list) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.chapter_view, list);
        listView.setAdapter(arrayAdapter);
    }


}