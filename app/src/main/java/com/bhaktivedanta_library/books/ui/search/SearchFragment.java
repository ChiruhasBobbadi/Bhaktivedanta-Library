package com.bhaktivedanta_library.books.ui.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhaktivedanta_library.books.Model.SearchModel;
import com.bhaktivedanta_library.books.R;
import com.bhaktivedanta_library.books.dao.level1.pages.Level1_Pages;
import com.bhaktivedanta_library.books.dao.level2.pages.Level2_Pages;
import com.bhaktivedanta_library.books.dao.level3.pages.Level3_Pages;
import com.bhaktivedanta_library.books.dao.tags.Tags;
import com.bhaktivedanta_library.books.dao.tags.TagsViewModel;
import com.bhaktivedanta_library.books.helper.ToolBarNameHelper;
import com.bhaktivedanta_library.books.ui.quick_access.QuickAccessViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static android.widget.LinearLayout.VERTICAL;


public class SearchFragment extends Fragment implements SearchAdapter.ItemListener {


    private static final String TAG = "SearchFragment";
    SearchAdapter adapter;
    SearchViewModel viewModel;
    TagsViewModel tagsViewModel;
    List<SearchModel> searchResults;
    SharedPreferences.Editor editor;
    QuickAccessViewModel quickAccessViewModel;
    SharedPreferences sharedpreferences;
    List<Tags> tags;
    ToolBarNameHelper helper;
    private Menu menu;
    private RecyclerView rv;
    private String searchKey;
    SearchView searchView;
    private String prevSearch,prevTag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        rv = root.findViewById(R.id.rv);
        init();
        return root;
    }

    private void init() {

        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        tagsViewModel = ViewModelProviders.of(this).get(TagsViewModel.class);
        quickAccessViewModel = ViewModelProviders.of(this).get(QuickAccessViewModel.class);
        sharedpreferences = this.getActivity().getSharedPreferences("dataStore",
                MODE_PRIVATE);
        editor = sharedpreferences.edit();


        prevSearch = sharedpreferences.getString("searchKey", "");
        prevTag = sharedpreferences.getString("tagKey", "");
        helper = new ToolBarNameHelper();
        adapter = new SearchAdapter(this);
        DividerItemDecoration itemDecor = new DividerItemDecoration(getContext(), VERTICAL);
        itemDecor.setDrawable(getActivity().getResources().getDrawable(R.drawable.divider));
        rv.addItemDecoration(itemDecor);
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
        searchResults = new ArrayList<>();

    }




    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView= (SearchView) item.getActionView();
        searchView.setIconified(false);
        searchView.setQueryHint("Search #food, lord");
        searchView.setMaxWidth(Integer.MAX_VALUE);

        if(!prevSearch.equals("")){
            searchView.setQuery(prevSearch,true);
            searchWord(prevSearch);
            Log.d(TAG, "checkPrevSearch: true");
        }else if(!prevTag.equals("")){
            searchView.setQuery(prevTag,true);
            fetchTags(prevTag);
        }

        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = searchView.findViewById(searchPlateId);
        if (searchPlate != null) {
            searchPlate.setBackgroundColor(Color.TRANSPARENT);
            int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchResults.clear();
                adapter.setData(searchResults);

                searchKey = s;
                if (s.trim().startsWith("#"))
                    fetchTags(s);
                else if (s.equals("")) {
                    searchResults.clear();
                    adapter.setData(searchResults);
                } else {
                    searchWord(s.trim());
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.trim().startsWith("#") && s.length() > 1)
                    fetchTags(s);

                return false;
            }
        });

        searchView.setOnCloseListener(() -> {
            searchResults.clear();
            adapter.setData(searchResults);
            return false;
        });

    }

    private void searchWord(String string) {
        fetchL1Results(string);
    }

    private void fetchL1Results(String string) {
        viewModel.getMatchedL1Pages(string).observe(getViewLifecycleOwner(), results -> {
            for (int i = 0; i < results.size(); i++) {
                Level1_Pages page = results.get(i);
                searchResults.add(new SearchModel(page.getPageNumber(), page.getBookName(), page.getChapterName(), 1, helper.getL1TitleName(page.getBookName(), page.getPageNumber())));
            }
            fetchL2Results(string);
        });
    }

    private void fetchL2Results(String string) {
        viewModel.getMatchedL2Pages(string).observe(getViewLifecycleOwner(), results -> {
            for (int i = 0; i < results.size(); i++) {
                Level2_Pages page = results.get(i);
                if (page.getBookName().equals("Bhagavad-gītā As It Is"))
                    searchResults.add(new SearchModel(page.getPageNumber(), page.getBookName(), page.getVerseName(),
                            2, helper.getL2TitleName(page.getBookName(), 0, page.getVerseName())));
                else
                    searchResults.add(new SearchModel(page.getPageNumber(), page.getBookName(), page.getVerseName(),
                            2, helper.getL2TitleName(page.getBookName(), page.getChapter(), page.getVerse() + "")));
            }
            fetchL3Results(string);
        });
    }

    private void fetchL3Results(String string) {
        viewModel.getMatchedL3Pages(string).observe(getViewLifecycleOwner(), results -> {
            for (int i = 0; i < results.size(); i++) {
                Level3_Pages page = results.get(i);
                searchResults.add(new SearchModel(page.getPageNumber(), page.getBookName(), page.getVerseName(), 3, helper.getL3TitleName(page.getVerseName())));
            }


            adapter.setData(searchResults);
        });
    }

    private void tagSearch(String string) {

    }


    private void fetchTags(String tagName) {
        searchKey = tagName;
        tagsViewModel.getTagsByName(tagName).observe(getViewLifecycleOwner(), tags -> {
            this.tags = tags;

            List<SearchModel> sm = new ArrayList<>();

            for (int i = 0; i < tags.size(); i++) {
                Tags t = this.tags.get(i);
                if (t.getLevel() == 1)
                    sm.add(new SearchModel(t.getPageNumber(), t.getBookName(), t.getLastLevel(), t.getLevel(), helper.getL1TitleName(t.getBookName(), t.getPageNumber())));
                else if (t.getLevel() == 2) {
                    if (t.getBookName().equals("Bhagavad-gītā As It Is"))
                        sm.add(new SearchModel(t.getPageNumber(), t.getBookName(),
                                t.getLastLevel(), t.getLevel(), helper.getL2TitleName(t.getBookName(), 0, t.getLastLevel())));
                    else
                        // todo
                        sm.add(new SearchModel(t.getPageNumber(), t.getBookName(),
                                t.getLastLevel(), t.getLevel(), helper.getL2TitleName(t.getBookName(), 0, t.getLastLevel())));
                } else
                    sm.add(new SearchModel(t.getPageNumber(), t.getBookName(), t.getLastLevel(), t.getLevel(), helper.getL3TitleName(t.getLastLevel())));

            }


            adapter.setData(sm);
        });
    }


    @Override
    public void itemClicked(SearchModel bookmark, View v) {

        if(getActivity().getCurrentFocus()!=null){
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
        NavController controller = Navigation.findNavController(v);

        editor.putString("bookName", bookmark.getBookName());
        editor.putString("searchKey",searchKey.startsWith("#")?"":searchKey);
        editor.putString("tagKey",searchKey.startsWith("#")?searchKey:"");
        Log.d(TAG, "itemClicked: " + searchKey);
        editor.apply();
        Bundle bundle = new Bundle();
        bundle.putString("title", bookmark.getDisplayName());
        bundle.putString("searchKey", searchKey);
        switch (bookmark.getLevel()) {
            case 1:
                quickAccessViewModel.l1Repo.updateCurrentPage(bookmark.getBookName(), bookmark.getPageNumer());
                controller.navigate(R.id.action_navigation_search_to_l1Fragment, bundle);
                break;
            case 2:
                quickAccessViewModel.l2Repo.updateCurrentPage(bookmark.getBookName(), bookmark.getPageNumer());
                controller.navigate(R.id.action_navigation_search_to_l2Fragment, bundle);
                break;
            case 3:
                quickAccessViewModel.l3Repo.updateCurrentPage(bookmark.getBookName(), bookmark.getPageNumer());
                controller.navigate(R.id.action_navigation_search_to_l3Fragment, bundle);
                break;

        }

    }


}