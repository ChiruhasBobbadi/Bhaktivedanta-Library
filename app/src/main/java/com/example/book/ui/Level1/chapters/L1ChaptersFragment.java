package com.example.book.ui.Level1.chapters;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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


public class L1ChaptersFragment extends Fragment {


    private static final String TAG = "L1ChaptersFragment";
    SharedPreferences sharedpreferences;
    String bookName;
    L1ChaptersViewModel viewModel;

    ListView listView;
    View v;
    List<LastLevelModel> model;

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

            String chap = model.get(position).getLastLevelName().split("\\.")[1].trim();
            v = view;
            viewModel.getPageNumberOfChapter(bookName, chap).observe(getViewLifecycleOwner(), number -> {

                Bundle bundle = new Bundle();
                bundle.putString("pageNumber", number + "-" + "level1");
                bundle.putString("title", bookName);

                NavController controller = Navigation.findNavController(view);

                controller.navigate(R.id.action_l1ChaptersFragment_to_l1Fragment, bundle);

            });


        });
    }

    private void viewModelCalls() {
        viewModel = ViewModelProviders.of(this).get(L1ChaptersViewModel.class);

        viewModel.getChapters(bookName).observe(getViewLifecycleOwner(), strings -> {

            model = new ArrayList<>();
            for (int i = 0; i < strings.size(); i++)
                model.add(new LastLevelModel((i + 1) + ". " + strings.get(i).getChapterName(), strings.get(i).getTranslation().replace("¶", "")));


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