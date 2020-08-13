package com.bhaktivedanta_library.books.ui.Level3.canto;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bhaktivedanta_library.books.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class L3Canto extends Fragment {
    private static final String TAG = "L3Canto";
    SharedPreferences sharedpreferences;
    String bookName;
    L3CantoViewModel viewModel;
    List<String> canto;
    ListView listView;
    View v;

    public L3Canto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_l3_canto, container, false);

        listView = root.findViewById(R.id.list);

        init();

        viewModelCalls();
        listItemClick();

        return root;
    }

    private void listItemClick() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String can = canto.get(position);
            v = view;
            String c = can.split("\\.")[1];
            Bundle bundle = new Bundle();
            bundle.putString("canto",c);
            bundle.putString("title",bookName);

            NavController controller = Navigation.findNavController(view);

            controller.navigate(R.id.action_l3Canto_to_l3ChapterFragment,bundle);




        });
    }

    private void viewModelCalls() {
        viewModel = ViewModelProviders.of(this).get(L3CantoViewModel.class);

        viewModel.getCanto(bookName).observe(getViewLifecycleOwner(), strings -> {

             canto = strings;
            for (int i = 0; i < canto.size(); i++) {
                canto.set(i,(i+1)+". "+canto.get(i));
            }

            createListView(canto);

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