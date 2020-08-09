package com.example.book.ui.Level3.Books;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.example.book.dao.bookmarks.Bookmarks;
import com.example.book.dao.bookmarks.BookmarksViewModel;
import com.example.book.dao.level3.pages.Level3_Pages;
import com.example.book.dao.tags.Tags;
import com.example.book.dao.tags.TagsViewModel;
import com.example.book.helper.TagDialog;
import com.example.book.ui.Level2.books.L2Fragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class L3Fragment extends Fragment implements L3Adapter.ItemListener, TagDialog.tagListener {

    private static final String TAG = "L3Fragment";
    TagsViewModel tagsViewModel;
    RecyclerView rv;
    L3Adapter adapter;
    SharedPreferences sharedpreferences;
    boolean _text, _syn, _trans, _purp,_textDefault, _textSmall, _textMedium, _textLarge;
    boolean isBookmark;
    boolean isFromVerse = false;
    View view;
    private L3ViewModel viewModel;
    private String bookName;
    private Level3_Pages currPage;
    private int scrollTo = -1;
    private Menu menu;

    BookmarksViewModel bookmarksViewModel;
    private int current_page;
    private ChipGroup chipGroup;
    private TagDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_l3, container, false);
        view = root;
        chipGroup = root.findViewById(R.id.container);
        checkIfFromVerse();
        getBookName();

        initRecyclerView();

        viewModelCall();


        return root;
    }

    private void viewModelCall() {
        bookmarksViewModel = ViewModelProviders.of(this).get(BookmarksViewModel.class);
        viewModel = ViewModelProviders.of(this).get(L3ViewModel.class);
        tagsViewModel = ViewModelProviders.of(this).get(TagsViewModel.class);
        if (!isFromVerse)
            viewModel.getCurrentPage(bookName).observe(getViewLifecycleOwner(), integer -> {
                Log.d(TAG, "viewModelCall: "+integer);
                if (scrollTo == -1) {
                    rv.scrollToPosition(integer - 1);
                    scrollTo = integer;

                }

            });
        else {
            rv.scrollToPosition(scrollTo - 1);
        }


        viewModel.getPages(bookName).observe(getViewLifecycleOwner(), level3_pages -> {
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

            adapter.setData(level3_pages, _text, _purp, _trans, _syn,res,temp);
            rv.setAdapter(adapter);
        });
    }

    private void initRecyclerView() {
        rv = view.findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new L3Adapter(this, getActivity());


        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv);
    }

    private void checkIfFromVerse() {

        if (getArguments().containsKey("pageNumber") && getArguments().getString("pageNumber") != null) {
            String t = getArguments().getString("pageNumber");

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
        current_page = currentPage;
        Log.d(TAG, "onPageChange: page updated");
        viewModel.updateCurrentPage(bookName, currentPage);
    }

    @Override
    public void itemChanged(Level3_Pages page) {
        currPage = page;
        Log.d(TAG, "itemChanged: ");

        bookmarksViewModel.isBookmark(currPage.getBookName(), currPage.getLevel_3_Name(), currPage.getChapterName(), currPage.getVerseName()).observe(getViewLifecycleOwner(), bookmarked -> {
            Log.d(TAG, "isBookmark "+bookmarked);
            if (bookmarked) {
                menu.getItem(1).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.bookmark));
                isBookmark = true;
            } else {
                menu.getItem(1).setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.bookmark_border));
                isBookmark = false;
            }
        });

        chipGroup.removeAllViews();
        chipGroup.setVisibility(View.GONE);

        tagsViewModel.getTagsOfPage(bookName, currPage.getVerseName(), currPage.getPageNumber()).observe(getViewLifecycleOwner(), list -> {
            chipGroup.removeAllViews();
            if (list.size() > 0)
                chipGroup.setVisibility(View.VISIBLE);
            setTag(list);
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

                Drawable bookmark = menu.getItem(1).getIcon();
                Log.d(TAG, "onOptionsItemSelected: " + bookmark.toString());

                Bookmarks bkmk = new Bookmarks(
                        currPage.getLevel_3_Name(), currPage.getChapterName(), 3,
                        current_page, currPage.getVerseName(), currPage.getBookName()
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

                controller.navigate(R.id.action_l3Fragment_to_l3Canto);
                return true;

            case R.id.tag:
                openDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void openDialog() {
        dialog = new TagDialog();

        dialog.setTargetFragment(L3Fragment.this, 300);
        dialog.show(getFragmentManager(), "tag open");
    }

    @Override
    public void onDialogSubmit(String tag) {
        if (!tag.startsWith("#")) {
            Toast.makeText(getActivity(), "Tag should start with #", Toast.LENGTH_LONG).show();
        } else if (tag.contains(" "))
            Toast.makeText(getContext(), "Tag should not contain space", Toast.LENGTH_SHORT).show();
        else {

            if (tag.length() == 1)
                Toast.makeText(getContext(), "Please add a valid tag name", Toast.LENGTH_SHORT).show();
            else {
                addTag(new Tags(tag, bookName, 2, currPage.getVerseName(), currPage.getPageNumber()));
                Log.d(TAG, "onFinishEditDialog: ");
            }


        }
    }

    private void addTag(Tags tag) {
        Log.d(TAG, "addTag: ");
        tagsViewModel.addTag(tag);
        dialog.dismiss();
    }

    private void setTag(final List<String> tagList) {

        for (int index = 0; index < tagList.size(); index++) {
            final String tagName = tagList.get(index);
            final Chip chip = new Chip(getContext());
            int paddingDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 10,
                    getResources().getDisplayMetrics()
            );
            chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
            chip.setText(tagName);
            chip.setCloseIconResource(R.drawable.close);
            chip.setCloseIconEnabled(true);
            //Added click listener on close icon to remove tag from ChipGroup
            chip.setOnCloseIconClickListener(v -> {
                tagsViewModel.deleteTag(tagName, bookName, currPage.getChapterName());
                tagList.remove(tagName);
                chipGroup.removeView(chip);
                Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 20 milliseconds
                vibrator.vibrate(20);
            });

            chipGroup.addView(chip);
        }

    }
}