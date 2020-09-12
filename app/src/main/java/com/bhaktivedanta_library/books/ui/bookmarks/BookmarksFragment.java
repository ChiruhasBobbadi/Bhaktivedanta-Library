package com.bhaktivedanta_library.books.ui.bookmarks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhaktivedanta_library.books.R;
import com.bhaktivedanta_library.books.dao.bookmarks.Bookmarks;
import com.bhaktivedanta_library.books.ui.quick_access.QuickAccessViewModel;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static android.widget.LinearLayout.VERTICAL;

public class BookmarksFragment extends Fragment implements BookmarksAdapter.ItemListener {

    private BookmarksViewModel bookmarksViewModel;
    private List<Bookmarks> bookmarks;
    SharedPreferences sharedpreferences;
    View view;
    BookmarksAdapter adapter;
    RecyclerView rv;
    SharedPreferences.Editor editor;
    QuickAccessViewModel quickAccessViewModel;
    private static final String TAG = "BookmarksFragment";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       view  = inflater.inflate(R.layout.fragment_bookmarks, container, false);
       rv = view.findViewById(R.id.rv);
        init();
        nullifyPrevSearch();
        viewModelCall();
        return view;
    }

    private void init() {
        DividerItemDecoration itemDecor = new DividerItemDecoration(getContext(), VERTICAL);
        itemDecor.setDrawable(getActivity().getResources().getDrawable( R.drawable.divider ));
        rv.addItemDecoration(itemDecor);
        adapter = new BookmarksAdapter(this);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
        if(getActivity().getCurrentFocus()!=null){
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    private void viewModelCall() {
        bookmarksViewModel =
                ViewModelProviders.of(this).get(BookmarksViewModel.class);

        quickAccessViewModel =  ViewModelProviders.of(this).get(QuickAccessViewModel.class);

        bookmarksViewModel.getAllBookmarks().observe(getViewLifecycleOwner(),bookmarks->{

            adapter.setData(bookmarks);
            this.bookmarks = bookmarks;
        });


    }
    private void nullifyPrevSearch() {
        sharedpreferences = this.getActivity().getSharedPreferences("dataStore",
                MODE_PRIVATE);
        editor = sharedpreferences.edit();
        editor.putString("searchKey","");
        editor.putString("tagKey","");
        editor.apply();
    }
    @Override
    public void itemClicked(Bookmarks bookmark,View v) {
        NavController controller = Navigation.findNavController(v);

        editor.putString("bookName", bookmark.getBookName());
        editor.apply();
        Bundle bundle = new Bundle();
        bundle.putString("title", bookmark.getDisplayName());
        switch (bookmark.getLevel()){
            case 1:
                quickAccessViewModel.l1Repo.updateCurrentPage(bookmark.getBookName(),bookmark.getPageNumber());
                controller.navigate(R.id.action_navigation_bookmarks_to_l1Fragment,bundle);
                break;
            case 2:
                quickAccessViewModel.l2Repo.updateCurrentPage(bookmark.getBookName(),bookmark.getPageNumber());
                controller.navigate(R.id.action_navigation_bookmarks_to_l2Fragment,bundle);
                break;
            case 3:
                quickAccessViewModel.l3Repo.updateCurrentPage(bookmark.getBookName(),bookmark.getPageNumber());
                controller.navigate(R.id.action_navigation_bookmarks_to_l3Fragment,bundle);
                break;

        }
    }
}