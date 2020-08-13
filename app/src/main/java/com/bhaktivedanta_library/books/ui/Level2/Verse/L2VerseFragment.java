package com.bhaktivedanta_library.books.ui.Level2.Verse;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bhaktivedanta_library.books.Model.LastLevelModel;
import com.bhaktivedanta_library.books.R;
import com.bhaktivedanta_library.books.helper.Chapteradapter;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class L2VerseFragment extends Fragment {

    private static final String TAG = "L2ChaptersFragment";
    SharedPreferences sharedpreferences;
    String bookName;
    L2VerseViewModel viewModel;
    List<String> verses;
    ListView listView;
    View v;
    String chapter;
    List<LastLevelModel> model;
    public L2VerseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_l2_verse, container, false);
        init();
        listView = root.findViewById(R.id.list);
        viewModelCalls();
        listItemClick();

        return root;
    }

    private void listItemClick() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String verse = model.get(position).getLastLevelName().split("\\.",2)[1].trim();
            v = view;


            viewModel.getPageNumberOfVerse(bookName, chapter.trim(), verse).observe(getViewLifecycleOwner(), number -> {

                Bundle bundle = new Bundle();
                bundle.putString("pageNumber", number + "-" + "level2");
                bundle.putString("title", bookName);

                NavController controller = Navigation.findNavController(view);

                controller.navigate(R.id.action_l2VerseFragment_to_l2Fragment, bundle);

            });


        });
    }

    private void viewModelCalls() {
        viewModel = ViewModelProviders.of(this).get(L2VerseViewModel.class);

        chapter = getArguments().getString("chapter").trim();
        Log.d(TAG, "viewModelCalls: "+chapter);

        viewModel.getNavVerses(bookName,chapter).observe(getViewLifecycleOwner(), strings -> {

            model = new ArrayList<>();
            for (int i = 0; i < strings.size(); i++)
                model.add(new LastLevelModel((i + 1) + ". " + strings.get(i).getVerseName(), strings.get(i).getTranslation().replace("¶", "")));


            createListView();

        });


    }

    private void init() {

        sharedpreferences = this.getActivity().getSharedPreferences("dataStore",
                MODE_PRIVATE);
        bookName = sharedpreferences.getString("bookName", "");
    }

    public void createListView() {
        Chapteradapter chapteradapter = new Chapteradapter(getActivity(), model);
        listView.setAdapter(chapteradapter);
    }
}