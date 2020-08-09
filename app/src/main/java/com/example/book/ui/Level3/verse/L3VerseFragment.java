package com.example.book.ui.Level3.verse;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.book.Model.LastLevelModel;
import com.example.book.R;
import com.example.book.helper.Chapteradapter;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class L3VerseFragment extends Fragment {

    SharedPreferences sharedpreferences;
    String bookName;
    L3VerseViewModel viewModel;
    List<String> verses;
    ListView listView;
    View v;
    String chapter;
    List<LastLevelModel> model;
    private static final String TAG = "L3VerseFragment";
    String canto;
    public L3VerseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_l3_verse, container, false);
        init();
        listView = root.findViewById(R.id.list);
        viewModelCalls();
        listItemClick();

        return root;
    }

    private void listItemClick() {
        listView.setOnItemClickListener((parent, view, position, id) -> {

            String verse = model.get(position).getLastLevelName().split("\\.",2)[1].trim();

            Log.d(TAG, "listItemClick: "+verse);
            v = view;
            Log.d(TAG, "listItemClick: "+verse);

            viewModel.getPageNumberOfVerse(bookName,canto,chapter.trim(), verse).observe(getViewLifecycleOwner(), number -> {

                Bundle bundle = new Bundle();
                Log.d(TAG, "pageNumber: "+number);
                bundle.putString("pageNumber", number + "-" + "level3");
                bundle.putString("title", bookName);

                NavController controller = Navigation.findNavController(view);

                controller.navigate(R.id.action_l3VerseFragment_to_l3Fragment, bundle);

            });


        });
    }

    private void viewModelCalls() {
        viewModel = ViewModelProviders.of(this).get(L3VerseViewModel.class);

        chapter = getArguments().getString("chapter").trim();
        Log.d(TAG, "viewModelCalls: "+chapter);
        canto = getArguments().getString("canto").trim();
        viewModel.getNavVerses(bookName,canto,chapter).observe(getViewLifecycleOwner(), strings -> {

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