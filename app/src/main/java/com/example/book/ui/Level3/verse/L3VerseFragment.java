package com.example.book.ui.Level3.verse;

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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.book.R;
import com.example.book.ui.Level2.Verse.L2VerseViewModel;

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

            String verse = verses.get(position).split("\\.",2)[1].trim();
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
        canto = getArguments().getString("canto").trim();
        viewModel.getVerses(bookName,canto, chapter).observe(getViewLifecycleOwner(), strings -> {

                List<String> list = new ArrayList<>();

                for (int i = 0; i < strings.size(); i++) {
                    list.add(i, ((i+1)+". "+strings.get(i)));
                }

            verses =list;
            createListView(list);

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