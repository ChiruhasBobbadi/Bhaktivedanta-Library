package com.example.book.ui.Level1.Books;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.book.R;
import com.example.book.dao.level1.pages.Level1_Pages;

import static android.content.Context.MODE_PRIVATE;

public class L1Fragment extends Fragment implements L1Adapter.ItemListener {
    private static final String TAG = "L1Fragment";
    RecyclerView rv;
    L1Adapter adapter;
    SharedPreferences sharedpreferences;
    boolean _text, _syn, _trans, _purp;
    private L1ViewModel viewModel;
    private String bookName;
    private Level1_Pages currPage;
    private int scrollTo = -1;
    private Menu menu;
    boolean isBookmark;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_l1, container, false);
        view = root;
        getBookName();

        /*RecyclerView.SmoothScroller smoothScroller = new
                LinearSmoothScroller(getActivity()) {
                    @Override protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };
        smoothScroller.setTargetPosition(scrollTo);*/
        rv = root.findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new L1Adapter(this);

      /*  LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
        linearSnapHelper.attachToRecyclerView(rv);*/

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv);

        viewModel = ViewModelProviders.of(this).get(L1ViewModel.class);

        viewModel.getCurrentPage(bookName).observe(getViewLifecycleOwner(), integer -> {

            if (scrollTo == -1) {
                rv.scrollToPosition(integer - 1);
                scrollTo = integer;
            }

        });


        viewModel.getPages(bookName).observe(getViewLifecycleOwner(), level1_pages -> {
            adapter.setData(level1_pages, _text, _purp, _trans, _syn);
            rv.setAdapter(adapter);
        });


        return root;
    }

    private void getBookName() {
        sharedpreferences = this.getActivity().getSharedPreferences("dataStore",
                MODE_PRIVATE);
        bookName = sharedpreferences.getString("bookName", "");

        _text = sharedpreferences.getBoolean("text", true);
        _purp = sharedpreferences.getBoolean("purport", true);
        _trans = sharedpreferences.getBoolean("translation", true);
        _syn = sharedpreferences.getBoolean("synonyms", true);

    }




    @Override
    public void onItemClick(int currentPage) {
        viewModel.updateCurrentPage(bookName, currentPage);
    }

    @Override
    public void itemChanged(Level1_Pages page) {
        currPage = page;
        Log.d(TAG, "itemChanged: ");
        /**
         *  if bookmarked => change to solid
         *  else change to bordered icon
         */

        /*        if(isBookmark){
            //menu.getItem(1).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.bookmark));
            isBookmark=true;
        }else{
            isBookmark=false;
        }*/


    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        this.menu = menu;
        inflater.inflate(R.menu.level1, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Log.d(TAG, "onOptionsItemSelected: clicked");

        switch (item.getItemId()){
            case R.id.bookmark:
                Drawable myIcon = getResources().getDrawable( R.drawable.bookmark );
                Drawable bookmark = menu.getItem(1).getIcon();
                Log.d(TAG, "onOptionsItemSelected: "+bookmark.toString());
                // un-bookmarking
                if(isBookmark){
                    // show popup to remove bookmark
                    //change icon
                    menu.getItem(1).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.bookmark_border));
                    // remove from db

                }else{
                    //change icon
                    menu.getItem(1).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.bookmark));
                    // add to db


                }
                isBookmark=!isBookmark;
            case R.id.list:
                NavController controller = Navigation.findNavController(view);
//                Bundle bundle = new Bundle();
//                bundle.putString("title", bookName);
                controller.navigate(R.id.action_l1Fragment_to_l1ChaptersFragment);
        }

        return super.onOptionsItemSelected(item);
    }
}