package com.example.book.ui.quick_access;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.book.R;
import com.example.book.ui.books.GridAdapter;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class QuickAccessFragment extends Fragment implements MaterialSpinner.OnItemSelectedListener, View.OnClickListener {
    private static final String TAG = "QuickAccessFragment";
    MaterialSpinner name, canto, chapter, verse;
    QuickAccessViewModel viewModel;
    List<String> books;
    List<String> chapters;
    List<String> verses;
    List<String> cantos;
    View view,navView;
    Button submit;
    TextView chapterName, cantoName, verseName;
    int level = -1;
    String bookName;
    String _chapter, _canto, _verse;
    NavController controller;
    Bundle bundle;
    SharedPreferences sharedpreferences;
    GridAdapter adapter;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_quick_access, container, false);

        init();


        viewModel = ViewModelProviders.of(this).get(QuickAccessViewModel.class);

        viewModel.getBooks().observe(getViewLifecycleOwner(), list -> {

            list.add(0, "Select Book Name");
            books = list;
            name.setItems(books);
        });


        return view;
    }

    private void init() {


        name = view.findViewById(R.id.name);
        cantoName = view.findViewById(R.id.cantoName);
        canto = view.findViewById(R.id.canto);
        chapterName = view.findViewById(R.id.chapterName);
        chapter = view.findViewById(R.id.chapter);
        verseName = view.findViewById(R.id.verseName);
        verse = view.findViewById(R.id.verse);

        submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(this);

        name.setOnItemSelectedListener(this);
        canto.setOnItemSelectedListener(this);
        chapter.setOnItemSelectedListener(this);
        verse.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {


        switch (view.getId()) {
            case R.id.name:
                // check if it is not zero
                if (position == 0)
                    Toast.makeText(getActivity(), "Please select a book to proceed", Toast.LENGTH_SHORT).show();
                else {
                    bookName = books.get(position);

                    viewModel.getLevel(bookName).observe(getViewLifecycleOwner(), level -> {
                        this.level = level;
                        switch (level) {
                            case 1:
                                setL1Chapters();
                                chapterName.setVisibility(View.VISIBLE);
                                chapter.setVisibility(View.VISIBLE);

                                verseName.setVisibility(View.GONE);
                                verse.setVisibility(View.GONE);
                                cantoName.setVisibility(View.GONE);
                                canto.setVisibility(View.GONE);
                                break;
                            case 2:
                                chapterName.setVisibility(View.VISIBLE);
                                chapter.setVisibility(View.VISIBLE);
                                verseName.setVisibility(View.VISIBLE);
                                verse.setVisibility(View.VISIBLE);
                                cantoName.setVisibility(View.GONE);
                                canto.setVisibility(View.GONE);
                                setL2Chapters();
                                break;
                            case 3:
                                chapterName.setVisibility(View.VISIBLE);
                                chapter.setVisibility(View.VISIBLE);
                                cantoName.setVisibility(View.VISIBLE);
                                canto.setVisibility(View.VISIBLE);
                                verseName.setVisibility(View.VISIBLE);
                                verse.setVisibility(View.VISIBLE);
                                setL3Canto();
                                break;

                        }
                    });
                }
                break;

            case R.id.canto:
                if (position == 0)
                    Toast.makeText(getActivity(), "Please select a Canto", Toast.LENGTH_SHORT).show();
                else {

                    _canto = cantos.get(position).trim();
                    setL3Chapters();
                }
                break;
            case R.id.chapter:
                if (position == 0)
                    Toast.makeText(getActivity(), "Please select  a chapter", Toast.LENGTH_SHORT).show();
                else {
                    _chapter = chapters.get(position).trim();
                    Log.d(TAG, "onItemSelected: " + _chapter);
                    if (level == 1) {
                        submit.setVisibility(View.VISIBLE);
                    } else if (level > 1) {
                        if (level == 2)
                            setL2Verse();
                        else
                            setL3Verse();

                        verseName.setVisibility(View.VISIBLE);
                        verse.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.verse:
                if (position == 0)
                    Toast.makeText(getActivity(), "Please select  a chapter", Toast.LENGTH_SHORT).show();
                else {
                    _verse = verses.get(position).trim();

                }
                submit.setVisibility(View.VISIBLE);
                break;
        }


    }

    private void setL1Chapters() {
        viewModel.l1Repo.getChapters(bookName).observe(getViewLifecycleOwner(), chap -> {

            List<String> temp = new ArrayList<>();
            temp.add("Select Chapter");
            chap.add(0, "Select Chapter");
            chapters = chap;
            for (int i = 1; i < chap.size(); i++) {
                temp.add((i) + ". " + chap.get(i));
            }
            chapter.setItems(temp);

        });
    }

    private void setL2Chapters() {
        viewModel.l2Repo.getChapters(bookName).observe(getViewLifecycleOwner(), chap -> {
            List<String> temp = new ArrayList<>();
            temp.add("Select Chapter");
            chap.add(0, "Select Chapter");
            chapters = chap;
            for (int i = 1; i < chap.size(); i++) {
                temp.add((i) + ". " + chap.get(i));
            }
            chapter.setItems(temp);
        });

    }

    private void setL3Chapters() {
        viewModel.l3Repo.getChapters(bookName, _canto).observe(getViewLifecycleOwner(), chap -> {
            List<String> temp = new ArrayList<>();
            temp.add("Select Chapter");
            chap.add(0, "Select Chapter");
            chapters = chap;
            for (int i = 1; i < chap.size(); i++) {
                temp.add((i) + ". " + chap.get(i));
            }
            chapter.setItems(temp);
        });
        setL3Verse();
    }

    private void setL2Verse() {
        Log.d(TAG, "bookName " + bookName);
        Log.d(TAG, "setL2Verse: " + _chapter);
        viewModel.l2Repo.getVerses(bookName, _chapter).observe(getViewLifecycleOwner(), ver -> {
            Log.d(TAG, "setL2Verse: "+ver);
            List<String> temp = new ArrayList<>();
            temp.add("Select Verse");
            ver.add(0, "Select Verse");
            verses = ver;
            for (int i = 1; i < ver.size(); i++) {
                temp.add((i) + ". " + ver.get(i));
            }
            verse.setItems(temp);
        });
    }

    //todo
    private void setL3Verse() {
        viewModel.l3Repo.getVerses(bookName, _canto, _chapter).observe(getViewLifecycleOwner(), ver -> {


          /*  chap.add(0,"Select chapter");
            chapter.setItems(chap);
            chapters = chap;*/
        });
    }

    private void setL3Canto() {
        viewModel.l3Repo.getCanto(bookName).observe(getViewLifecycleOwner(), can -> {
            List<String> temp = new ArrayList<>();
            temp.add("Select Canto");
            can.add(0, "Select Canto");
            cantos = can;
            for (int i = 1; i < can.size(); i++) {
                temp.add((i) + ". " + can.get(i));
            }
            canto.setItems(temp);
        });
        setL3Chapters();
    }

    public void getL1PageNumber() {
        viewModel.l1Repo.getPageNumberOfChapter(bookName,_chapter).observe(getViewLifecycleOwner(), integer -> {
            viewModel.l1Repo.updateCurrentPage(bookName,integer);
            controller.navigate(R.id.action_quick_access_to_l1Fragment,bundle);
        });
    }

    public void getL2PageNumber() {
        viewModel.l2Repo.getPageNumberOfVerse(bookName,_chapter,_verse).observe(getViewLifecycleOwner(), integer -> {
            viewModel.l2Repo.updateCurrentPage(bookName,integer);
            controller.navigate(R.id.action_quick_access_to_l2Fragment,bundle);
        });
    }

    public void getL3PageNumber() {
        viewModel.l1Repo.getPageNumberOfChapter(bookName,_chapter).observe(getViewLifecycleOwner(), integer -> {
            viewModel.l1Repo.updateCurrentPage(bookName,integer);
            controller.navigate(R.id.action_quick_access_to_l3Fragment,bundle);
        });
    }


    @Override
    public void onClick(View v) {
        setBookName();
        navView=v;
        controller = Navigation.findNavController(navView);
        // navigate to appropriate page
        bundle = new Bundle();
        bundle.putString("title", bookName);

        if (level == 1 && _chapter != null)
            getL1PageNumber();
        else if (level == 2 && (_chapter != null && _verse != null))
            getL2PageNumber();
        else if (level == 3 && (_canto != null && _chapter != null && _verse != null))
            getL3PageNumber();
        else
            Toast.makeText(getActivity(), "Please select all fields", Toast.LENGTH_SHORT).show();

    }
    void setBookName(){
        sharedpreferences = this.getActivity().getSharedPreferences("dataStore",
                MODE_PRIVATE);
        editor = sharedpreferences.edit();
        editor.putString("bookName", bookName);
        editor.apply();
    }
}