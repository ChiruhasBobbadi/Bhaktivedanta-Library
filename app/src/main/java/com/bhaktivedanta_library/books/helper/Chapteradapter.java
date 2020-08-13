package com.bhaktivedanta_library.books.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bhaktivedanta_library.books.Model.LastLevelModel;
import com.bhaktivedanta_library.books.R;

import java.util.List;

public class Chapteradapter extends ArrayAdapter<LastLevelModel> {

    List<LastLevelModel> list;
    public Chapteradapter(@NonNull Context context,  @NonNull List<LastLevelModel> objects) {
        super(context,0, objects);

        list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if(list.get(position).getTranslation()!=null || listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.index_view,parent,false);

            TextView name = listItemView.findViewById(R.id.name);
            TextView translation = listItemView.findViewById(R.id.translation);
            name.setText(list.get(position).getLastLevelName());
            translation.setText(list.get(position).getTranslation());
        }

        if(list.get(position).getTranslation()==null || listItemView==null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.chapter_view, parent, false);
            TextView name = listItemView.findViewById(R.id.name);
            name.setText(list.get(position).getLastLevelName());
        }

       return listItemView;

    }
}
