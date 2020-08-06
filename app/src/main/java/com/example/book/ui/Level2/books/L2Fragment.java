package com.example.book.ui.Level2.books;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.book.R;
import com.example.book.dao.bookmarks.Bookmarks;
import com.example.book.dao.bookmarks.BookmarksViewModel;
import com.example.book.dao.level1.pages.Level1_Pages;
import com.example.book.dao.level2.pages.Level2_Pages;
import com.example.book.ui.Level1.Books.L1Adapter;
import com.example.book.ui.Level1.Books.L1ViewModel;

import static android.content.Context.MODE_PRIVATE;


public class L2Fragment extends Fragment implements L2Adapter.ItemListener {
    private static final String TAG = "L2Fragment";
    RecyclerView rv;
    L2Adapter adapter;
    SharedPreferences sharedpreferences;
    boolean _text, _syn, _trans, _purp,_textDefault, _textSmall, _textMedium, _textLarge;
    private L2ViewModel viewModel;
    private String bookName;
    private Level2_Pages currPage;
    private int scrollTo = -1;
    private Menu menu;
    boolean isBookmark;
    boolean isFromVerse = false;
    View view;
    BookmarksViewModel bookmarksViewModel;
    private int currentPageNumber;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_l2, container, false);
        view = root;
        checkIfFromVerse();
        getBookName();

        initRecyclerView();

        viewModelCall();


        return root;
    }

    private void viewModelCall() {
        bookmarksViewModel = ViewModelProviders.of(this).get(BookmarksViewModel.class);
        viewModel = ViewModelProviders.of(this).get(L2ViewModel.class);

        if (!isFromVerse)
            viewModel.getCurrentPage(bookName).observe(getViewLifecycleOwner(), integer -> {

                if (scrollTo == -1) {
                    rv.scrollToPosition(integer - 1);
                    scrollTo = integer;
                }

            });
        else {
            rv.scrollToPosition(scrollTo - 1);
        }


        viewModel.getPages(bookName).observe(getViewLifecycleOwner(), level2_pages -> {

            String res="";
            if (_textDefault)
                res="textDefault";

            else if (_textSmall)
                res="textSmall";
            else if (_textMedium)
                res="textMedium";
            else if (_textLarge)
                res="textLarge";

            String temp = getArguments().getString("searchKey");

            adapter.setData(level2_pages, _text, _purp, _trans, _syn,res,temp);
            rv.setAdapter(adapter);
        });
    }

    private void initRecyclerView() {
        rv = view.findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new L2Adapter(this, getActivity());


        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv);
    }

    private void checkIfFromVerse() {

        if (getArguments().containsKey("pageNumber") && getArguments().getString("pageNumber") != null) {
            String t = getArguments().getString("pageNumber");
            Log.d(TAG, "checkIfFromVerse: "+t);
            String a[] = t.split("-");

            if (a.length == 2) {
                isFromVerse = true;
                Log.d(TAG, "checkIfFromVerse: "+a[0]);
                scrollTo = Integer.parseInt(a[0]);
            }
        }


    }

    private void getBookName() {
        sharedpreferences = this.getActivity().getSharedPreferences("dataStore",
                MODE_PRIVATE);
        bookName = sharedpreferences.getString("bookName", "");

        _text = sharedpreferences.getBoolean("text", true);
        _purp = sharedpreferences.getBoolean("purport", true);
        _trans = sharedpreferences.getBoolean("translation", true);
        _syn = sharedpreferences.getBoolean("synonyms", true);
        _textDefault = sharedpreferences.getBoolean("textDefault", true);
        _textSmall = sharedpreferences.getBoolean("textSmall", false);
        _textMedium = sharedpreferences.getBoolean("textMedium", false);
        _textLarge = sharedpreferences.getBoolean("textLarge", false);

    }


    @Override
    public void onPageChange(int currentPage) {
        Log.d(TAG, "onPageChange: page updated");
        currentPageNumber = currentPage;
        viewModel.updateCurrentPage(bookName, currentPage);
    }

    @Override
    public void itemChanged(Level2_Pages page) {
        currPage = page;
        Log.d(TAG, "itemChanged: ");

        bookmarksViewModel.isBookmark(currPage.getBookName(), "", currPage.getChapterName(), currPage.getVerseName()).observe(getViewLifecycleOwner(), bookmarked -> {
            Log.d(TAG, "isBookmark "+bookmarked);
            if (bookmarked) {
                menu.getItem(1).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.bookmark));
                isBookmark = true;
            } else {
                menu.getItem(1).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.bookmark_border));
                isBookmark = false;
            }
        });


    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        this.menu = menu;
        inflater.inflate(R.menu.level1, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            //todo
            case R.id.bookmark:
                Drawable myIcon = getResources().getDrawable(R.drawable.bookmark);
                Drawable bookmark = menu.getItem(1).getIcon();
                Bookmarks bkmk = new Bookmarks(
                        "", currPage.getChapterName(), 2,currentPageNumber
                        , currPage.getVerseName(), currPage.getBookName()
                );
                // un-bookmarking
                if (isBookmark) {
                    // show popup to remove bookmark
                    //change icon
                    menu.getItem(1).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.bookmark_border));
                    // remove from db
                    bookmarksViewModel.removeBookmark(bkmk);
                    Toast.makeText(getActivity(), "Bookmark removed..", Toast.LENGTH_SHORT).show();
                } else {
                    //change icon
                    menu.getItem(1).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.bookmark));
                    // add to db
                    bookmarksViewModel.addBookmark(bkmk);
                    Toast.makeText(getActivity(), "Bookmark added..", Toast.LENGTH_SHORT).show();


                }
                isBookmark = !isBookmark;
                return true;
            case R.id.list:
                NavController controller = Navigation.findNavController(view);

                controller.navigate(R.id.action_l2Fragment_to_l2Chapters);
                return true;
                //todo
            /*case R.id.tag:
                Toast.makeText(getActivity(), "Under development", Toast.LENGTH_SHORT).show();
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}