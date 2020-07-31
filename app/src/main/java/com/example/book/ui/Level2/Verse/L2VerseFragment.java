package com.example.book.ui.Level2.Verse;

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
            String verse = verses.get(position);
            v = view;
            viewModel.getPageNumberOfVerse(bookName,getArguments().getString("chapter"),verse).observe(getViewLifecycleOwner(), number -> {

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

        viewModel.getVerses(getArguments().getString("title"), getArguments().getString("chapter")).observe(getViewLifecycleOwner(), strings -> {

            Log.d(TAG, "viewModelCalls: " + strings.size());
            verses = strings;

            //todo
            if(verses.get(0)==null){
                for (int i = 0; i < verses.size(); i++) {
                    verses.add(i,(i+1)+"");
                }
            }
            createListView(verses);

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