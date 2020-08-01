package com.example.book.ui.Level2.books;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book.R;
import com.example.book.dao.level2.pages.Level2_Pages;


import java.util.Arrays;
import java.util.List;

class L2PurportAdapter extends RecyclerView.Adapter {
    private static final String TAG = "L2L2PurportAdapter";
    Level2_Pages page;
    List<String> purport;
    int currentPosition;
    boolean _text, _synonyms, _translation, _purport;

    public void setData(Level2_Pages data, boolean text, boolean syn, boolean pu, boolean trans) {
        _text = text;
        _synonyms = syn;
        _purport = pu;
        _translation = trans;
        page = data;
        String purp = page.getPurport().replace("¶", "¶\n");
        purport = Arrays.asList(purp.split("\\$"));

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == 0) {
            view = inflater.inflate(R.layout.page, parent, false);
            return new L2PurportAdapter.ViewHolderRest(view);
        } else if (viewType == 1) {
            view = inflater.inflate(R.layout.purport, parent, false);
            return new L2PurportAdapter.ViewHolderPurport(view);
        } else {
            view = inflater.inflate(R.layout.poem, parent, false);
            return new L2PurportAdapter.ViewHolderPoem(view);
        }


    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (position == 0) {

            L2PurportAdapter.ViewHolderRest rest = (L2PurportAdapter.ViewHolderRest) holder;

            if (_text && (page.getText() != null && page.getText().length() != 0))
                rest.text.setText(page.getText());
            else
                rest.text.setVisibility(View.GONE);


            if (_synonyms && (page.getSynonyms() != null && page.getSynonyms().length() != 0))
                rest.synonyms.setText(page.getSynonyms());
            else
                rest.synonyms.setVisibility(View.GONE);



            //todo
            //rest.verse.setText(page.getVerse()+"");

            if (_translation && (page.getTranslation() != null && page.getTranslation().length() != 0))
                rest.translation.setText(page.getTranslation());
            else
                rest.translation.setVisibility(View.GONE);


        } else if (position % 2 != 0) {
            L2PurportAdapter.ViewHolderPurport purp = (L2PurportAdapter.ViewHolderPurport) holder;
            if (_purport && (page.getPurport() != null && page.getPurport().length() != 0)) {
                String r = purport.get(position - 1).replace("¶", "¶\n");
                purp.purport.setText(r);
            } else
                purp.purport.setVisibility(View.GONE);
        } else {
            L2PurportAdapter.ViewHolderPoem poem = (L2PurportAdapter.ViewHolderPoem) holder;
            if (_purport && (page.getPurport() != null && page.getPurport().length() != 0)) {
                poem.poem.setText(purport.get(position - 1));
            } else
                poem.poem.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        else if (position % 2 != 0)
            return 1;
        else
            return 2;
    }

    @Override
    public int getItemCount() {
        return purport.size() + 1;
    }

    class ViewHolderRest extends RecyclerView.ViewHolder {
        TextView text, synonyms, translation;

        public ViewHolderRest(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            synonyms = itemView.findViewById(R.id.synonyms);
            translation = itemView.findViewById(R.id.translation);

        }
    }

    class ViewHolderPurport extends RecyclerView.ViewHolder {
        TextView purport;

        public ViewHolderPurport(@NonNull View itemView) {
            super(itemView);
            purport = itemView.findViewById(R.id.purport);
        }
    }

    class ViewHolderPoem extends RecyclerView.ViewHolder {
        TextView poem;

        public ViewHolderPoem(@NonNull View itemView) {
            super(itemView);
            poem = itemView.findViewById(R.id.poem);
        }
    }
}
