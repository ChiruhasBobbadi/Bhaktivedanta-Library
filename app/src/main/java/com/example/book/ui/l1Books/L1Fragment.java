package com.example.book.ui.l1Books;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book.R;
import com.example.book.helper.SnapHelperOneByOne;

import static android.content.Context.MODE_PRIVATE;

public class L1Fragment extends Fragment implements L1Adapter.ItemListener {
    private static final String TAG = "L1Fragment";
    RecyclerView rv;
    L1Adapter adapter;
    SharedPreferences sharedpreferences;
    private L1ViewModel viewModel;
    private String bookName;
    private int scrollTo=-1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_l1, container, false);
        getBookName();

        getActivity().setTitle(bookName);
        RecyclerView.SmoothScroller smoothScroller = new
                LinearSmoothScroller(getActivity()) {
                    @Override protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };
        smoothScroller.setTargetPosition(scrollTo);
        rv = root.findViewById(R.id.rv);

        rv.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new L1Adapter(this::onItemClick);

        LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
        linearSnapHelper.attachToRecyclerView(rv);


        viewModel = ViewModelProviders.of(getActivity()).get(L1ViewModel.class);

        viewModel.getCurrentPage(bookName).observe(getViewLifecycleOwner(), integer -> {

            if(scrollTo==-1){
                rv.scrollToPosition(integer-1);
                scrollTo =integer;
            }

        });


        viewModel.getPages(bookName).observe(getViewLifecycleOwner(), level1_pages -> {
            adapter.setData(level1_pages);
            rv.setAdapter(adapter);
        });


        return root;
    }

    private void getBookName() {
        sharedpreferences = this.getActivity().getSharedPreferences("dataStore",
                MODE_PRIVATE);
        bookName = sharedpreferences.getString("bookName", "");

    }


    @Override
    public void onItemClick(int currentPage) {
        viewModel.updateCurrentPage(bookName, currentPage);
    }
}