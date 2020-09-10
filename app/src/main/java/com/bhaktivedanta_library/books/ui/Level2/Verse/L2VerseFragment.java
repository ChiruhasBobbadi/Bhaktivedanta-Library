package com.bhaktivedanta_library.books.ui.Level2.Verse;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bhaktivedanta_library.books.Model.LastLevelModel;
import com.bhaktivedanta_library.books.R;
import com.bhaktivedanta_library.books.helper.Chapteradapter;
import com.bhaktivedanta_library.books.helper.ToolBarNameHelper;

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
    Bundle bundle;

    public L2VerseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_l2_verse, container, false);
        init();
        changeName();
        listView = root.findViewById(R.id.list);
        viewModelCalls();
        listItemClick();

        return root;
    }

    private void listItemClick() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String verse = model.get(position).getLastLevelName().trim();
            v = view;
            Log.d(TAG, "listItemClick: " + verse);

            viewModel.getPageNumberOfVerse(bookName, chapter.trim(), verse).observe(getViewLifecycleOwner(), number -> {

                bundle = new Bundle();
                Log.d(TAG, "listItemClick: " + number);
                bundle.putString("pageNumber", number + "-" + "level2");
                //todo
                if (bookName.equals("Bhagavad-gītā As It Is")) {
                    bundle.putString("title", new ToolBarNameHelper().getL2TitleName(bookName, 0, verse));
                    NavController controller = Navigation.findNavController(view);
                    controller.navigate(R.id.action_l2VerseFragment_to_l2Fragment, bundle);
                } else
                    fetchL2Page(number, view);


            });


        });
    }

    private void fetchL2Page(int page, View view) {
        viewModel.getL2Page(bookName, page).observe(getViewLifecycleOwner(), l2Page -> {
            bundle.putString("title", new ToolBarNameHelper().getL2TitleName(bookName, l2Page.getChapter(), l2Page.getVerse() + ""));
            NavController controller = Navigation.findNavController(view);
            controller.navigate(R.id.action_l2VerseFragment_to_l2Fragment, bundle);
        });
    }

    private void viewModelCalls() {
        viewModel = ViewModelProviders.of(this).get(L2VerseViewModel.class);

        chapter = getArguments().getString("chapter").trim();
        Log.d(TAG, "viewModelCalls: " + chapter);

        viewModel.getNavVerses(bookName, chapter).observe(getViewLifecycleOwner(), strings -> {

            model = new ArrayList<>();
            for (int i = 0; i < strings.size(); i++) {

                String trans = strings.get(i).getTranslation();
                if (trans != null)
                    trans = trans.replace("¶", "");
                model.add(new LastLevelModel(strings.get(i).getVerseName(), trans));

            }


            createListView();

        });


    }

    private void init() {

        sharedpreferences = this.getActivity().getSharedPreferences("dataStore",
                MODE_PRIVATE);
        bookName = sharedpreferences.getString("bookName", "");
    }

    private void changeName() {
        if (bookName.equals("The Science of Self Realization") || bookName.equals("Life Comes from Life"))
            ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("Chapter");


    }

    public void createListView() {
        Chapteradapter chapteradapter = new Chapteradapter(getActivity(), model);
        listView.setAdapter(chapteradapter);
    }
}