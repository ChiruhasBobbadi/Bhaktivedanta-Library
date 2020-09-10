package com.bhaktivedanta_library.books.ui.books;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhaktivedanta_library.books.R;
import com.bhaktivedanta_library.books.dao.lookup.LookupTable;

import static android.content.Context.MODE_PRIVATE;

public class BooksFragment extends Fragment implements GridAdapter.ItemListener {
    private static final String TAG = "BooksFragment";

    private BooksViewModel booksViewModel;
    RecyclerView rv;
    SharedPreferences sharedpreferences;
    GridAdapter adapter;
    SharedPreferences.Editor editor;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_books, container, false);

        rv = root.findViewById(R.id.rv);

        adapter= new GridAdapter(this::onItemClick);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);

        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(adapter);
        booksViewModel =
                ViewModelProviders.of(getActivity()).get(BooksViewModel.class);


        booksViewModel.getAll().observe(getViewLifecycleOwner(), lookupTables -> adapter.setData(lookupTables));




        return root;
    }

    @Override
    public void onItemClick(LookupTable clicked,View v) {

        NavController controller = Navigation.findNavController(v);

        sharedpreferences = this.getActivity().getSharedPreferences("dataStore",
                MODE_PRIVATE);
        editor = sharedpreferences.edit();

        editor.putString("bookName", clicked.getBookName());
        editor.apply();

        if(clicked.getLevel()==1)
            controller.navigate(R.id.action_navigation_books_to_l1ChaptersFragment);
        else if (clicked.getLevel()==2)
            controller.navigate(R.id.action_navigation_books_to_l2Chapters);
        else
            controller.navigate(R.id.action_navigation_books_to_l3Canto);

    }


}