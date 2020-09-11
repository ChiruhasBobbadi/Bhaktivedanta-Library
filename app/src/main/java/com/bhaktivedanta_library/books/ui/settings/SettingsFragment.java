package com.bhaktivedanta_library.books.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bhaktivedanta_library.books.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment implements SwitchCompat.OnCheckedChangeListener, MaterialSpinner.OnItemSelectedListener {

    private static final String TAG = "SettingsFragment";
    SharedPreferences sharedpreferences;
    boolean _text, _purport, _synonyms, _translation, _isDarkMode, _keepAwake, _textDefault, _textSmall, _textMedium, _textLarge;
    SharedPreferences.Editor editor;

    private SwitchCompat text, purport, synonyms, translation, darkMode, keepAwake;
    private MaterialSpinner fontSize;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        nullifyPrevSearch();
       init(root);
       return root;
    }
    private void nullifyPrevSearch() {
        sharedpreferences = this.getActivity().getSharedPreferences("dataStore",
                MODE_PRIVATE);
        editor = sharedpreferences.edit();
        editor.putString("searchKey","");
        editor.putString("tagKey","");
        editor.apply();
    }
    private void init(View root) {
        fontSize = root.findViewById(R.id.fontSize);
        text = root.findViewById(R.id.text);
        purport = root.findViewById(R.id.purport);
        synonyms = root.findViewById(R.id.synonyms);
        translation = root.findViewById(R.id.translation);
        darkMode = root.findViewById(R.id.darkMode);
        keepAwake = root.findViewById(R.id.keepAwake);
        checkPrefs();
        text.setOnCheckedChangeListener(this);
        purport.setOnCheckedChangeListener(this);
        synonyms.setOnCheckedChangeListener(this);
        translation.setOnCheckedChangeListener(this);
        darkMode.setOnCheckedChangeListener(this);
        keepAwake.setOnCheckedChangeListener(this);

        text.setChecked(_text);
        purport.setChecked(_purport);
        synonyms.setChecked(_synonyms);
        translation.setChecked(_translation);
        darkMode.setChecked(_isDarkMode);
        keepAwake.setChecked(_keepAwake);

    }

    private void checkPrefs() {

        _text = sharedpreferences.getBoolean("text", true);
        _synonyms = sharedpreferences.getBoolean("synonyms", true);
        _translation = sharedpreferences.getBoolean("translation", true);
        _purport = sharedpreferences.getBoolean("purport", true);
        _isDarkMode = sharedpreferences.getBoolean("darkMode", false);
        _keepAwake = sharedpreferences.getBoolean("keepAwake", false);
        _textDefault = sharedpreferences.getBoolean("textDefault", true);
        _textSmall = sharedpreferences.getBoolean("textSmall", false);
        _textMedium = sharedpreferences.getBoolean("textMedium", false);
        _textLarge = sharedpreferences.getBoolean("textLarge", false);
        fontSize.setOnItemSelectedListener(this);
        fontSize.setItems("Default", "Small", "Medium", "Large");
        fontSize.setSelectedIndex(3);
        if (_textDefault)
            fontSize.setSelectedIndex(0);
        else if (_textSmall)
            fontSize.setSelectedIndex(1);
        else if (_textMedium)
            fontSize.setSelectedIndex(2);
        else if (_textLarge)
            fontSize.setSelectedIndex(3);
    }



    @Override
    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
        Map<Integer, String> map = new HashMap<>();
        map.put(R.id.text, "text");
        map.put(R.id.purport, "purport");
        map.put(R.id.synonyms, "synonyms");
        map.put(R.id.translation, "translation");
        map.put(R.id.darkMode, "darkMode");
        map.put(R.id.keepAwake, "keepAwake");

        if (isChecked) {
            editor.putBoolean(map.get(v.getId()), true);
            editor.apply();


            switch (v.getId()) {
                case R.id.darkMode:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
                case R.id.keepAwake:
                    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    break;

            }


        } else {
            editor.putBoolean(map.get(v.getId()), false);
            editor.apply();
            switch (v.getId()) {
                case R.id.darkMode:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
                case R.id.keepAwake:
                    getActivity().getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    break;

            }
        }
    }


    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        String a[] = new String[]{"textDefault", "textSmall", "textMedium", "textLarge"};

        Log.d(TAG, "onItemSelected: " + position);

        editor.putBoolean(a[position], true);

        for (int i = 0; i < a.length; i++)
            if (position != i)
                editor.putBoolean(a[i], false);

        editor.apply();
    }
}