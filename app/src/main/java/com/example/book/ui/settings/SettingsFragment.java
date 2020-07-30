package com.example.book.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.book.R;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment implements SwitchCompat.OnCheckedChangeListener {

    SharedPreferences sharedpreferences;
    boolean _text, _purport, _synonyms, _translation;
    SharedPreferences.Editor editor;
    private SettingsViewModel settingsViewModel;
    private SwitchCompat text, purport, synonyms, translation;
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

        text.setOnCheckedChangeListener(this);
        purport.setOnCheckedChangeListener(this);
        synonyms.setOnCheckedChangeListener(this);
        translation.setOnCheckedChangeListener(this);

        text.setChecked(_text);
        purport.setChecked(_purport);
        synonyms.setChecked(_synonyms);
        translation.setChecked(_translation);


        return root;
    }

    private void checkPrefs() {
        sharedpreferences = this.getActivity().getSharedPreferences("dataStore",
                MODE_PRIVATE);
        _text = sharedpreferences.getBoolean("text", false);
        _synonyms = sharedpreferences.getBoolean("synonyms", false);
        _translation = sharedpreferences.getBoolean("translation", false);
        _purport = sharedpreferences.getBoolean("purport", false);


    }


    @Override
    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
        editor = sharedpreferences.edit();

        Map<Integer,String> map = new HashMap<>();
        map.put(R.id.text,"text");
        map.put(R.id.purport,"purport");
        map.put(R.id.synonyms,"synonyms");
        map.put(R.id.translation,"translation");

        if (isChecked) {
            editor.putBoolean(map.get(v.getId()), true);
            editor.apply();
        } else {
            editor.putBoolean(map.get(v.getId()), false);
            editor.apply();
        }
    }
}