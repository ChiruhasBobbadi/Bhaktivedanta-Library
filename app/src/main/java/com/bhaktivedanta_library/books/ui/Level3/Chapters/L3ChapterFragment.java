package com.bhaktivedanta_library.books.ui.Level3.Chapters;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bhaktivedanta_library.books.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class L3ChapterFragment extends Fragment {

    private static final String TAG = "L3ChapterFragment";
    SharedPreferences sharedpreferences;
    String bookName;
    L3ChapterViewModel viewModel;
    List<String> chapters;
    ListView listView;
    View v;

    public L3ChapterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_l3_chapter, container, false);

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
            String c = chap.split("\\.")[1];
            Bundle bundle = new Bundle();
            bundle.putString("chapter",c);
            bundle.putString("title",bookName);
            bundle.putString("canto",getArguments().getString("canto").trim());

            NavController controller = Navigation.findNavController(view);

            controller.navigate(R.id.action_l3ChapterFragment_to_l3VerseFragment,bundle);




        });
    }

    private void viewModelCalls() {
        viewModel = ViewModelProviders.of(this).get(L3ChapterViewModel.class);



        viewModel.getChapters(bookName,getArguments().getString("canto").trim()).observe(getViewLifecycleOwner(), strings -> {

            chapters = strings;
            Log.d(TAG, "viewModelCalls: "+strings);
            for (int i = 0; i < chapters.size(); i++) {
                chapters.set(i,(i+1)+". "+chapters.get(i));
            }

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