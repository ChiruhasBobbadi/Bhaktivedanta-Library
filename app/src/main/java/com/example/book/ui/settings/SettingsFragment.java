package com.example.book.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
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

import com.example.book.R;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment implements SwitchCompat.OnCheckedChangeListener {

    SharedPreferences sharedpreferences;
    boolean _text, _purport, _synonyms, _translation,_isDarkMode,_keepAwake;
    SharedPreferences.Editor editor;
    private SettingsViewModel settingsViewModel;
    private SwitchCompat text, purport, synonyms, translation,darkMode,keepAwake;
    private static final String TAG = "SettingsFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);


        checkPrefs();


        text = root.findViewById(R.id.text);
        purport = root.findViewById(R.id.purport);
        synonyms = root.findViewById(R.id.synonyms);
        translation = root.findViewById(R.id.translation);
        darkMode = root.findViewById(R.id.darkMode);
        keepAwake = root.findViewById(R.id.keepAwake);

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


        return root;
    }

    private void checkPrefs() {
        sharedpreferences = this.getActivity().getSharedPreferences("dataStore",
                MODE_PRIVATE);
        _text = sharedpreferences.getBoolean("text", true);
        _synonyms = sharedpreferences.getBoolean("synonyms", true);
        _translation = sharedpreferences.getBoolean("translation", true);
        _purport = sharedpreferences.getBoolean("purport", true);
        _isDarkMode = sharedpreferences.getBoolean("darkMode",false);
        _keepAwake = sharedpreferences.getBoolean("keepAwake",false);

    }


    @Override
    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
        editor = sharedpreferences.edit();

        Map<Integer,String> map = new HashMap<>();
        map.put(R.id.text,"text");
        map.put(R.id.purport,"purport");
        map.put(R.id.synonyms,"synonyms");
        map.put(R.id.translation,"translation");
        map.put(R.id.darkMode,"darkMode");
        map.put(R.id.keepAwake,"keepAwake");

        if (isChecked) {
            editor.putBoolean(map.get(v.getId()), true);
            editor.apply();


            switch (v.getId()){
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
            switch (v.getId()){
                case R.id.darkMode:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
                case R.id.keepAwake:
                    getActivity().getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    break;

            }
        }
    }
}