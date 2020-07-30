package com.example.book.ui.Level1.Books;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book.R;
import com.example.book.dao.level1.pages.Level1_Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class L1Adapter extends RecyclerView.Adapter<L1Adapter.ViewHolder> {


    private static final String TAG = "L1Adapter";
    List<Integer> images;
    Map<String, Integer> map;
    boolean _text, _synonyms, _translation, _purport;
    int curPage;
    private L1Adapter.ItemListener myListener;
    private List<Level1_Pages> l1Pages;

    public L1Adapter(L1Adapter.ItemListener listener) {
        myListener = listener;
        l1Pages = new ArrayList<>();
    }

    public void setListener(L1Adapter.ItemListener listener) {
        myListener = listener;
    }


    @Override
    public L1Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.page, parent, false));
    }

    @Override
    public int getItemCount() {
        return l1Pages.size();
    }

    @Override
    public void onBindViewHolder(L1Adapter.ViewHolder holder, int position) {

        Vibrator v = (Vibrator) holder.itemView.getContext().getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 20 milliseconds
        v.vibrate(20);

        Level1_Pages page = l1Pages.get(position);


        if (_text && ( page.getText()!=null && page.getText().length()!=0))
            holder.text.setText(page.getText());
        else
            holder.text.setVisibility(View.GONE);


        if (_synonyms && ( page.getSynonyms()!=null && page.getSynonyms().length()!=0))
            holder.synonyms.setText(page.getSynonyms());
        else
            holder.synonyms.setVisibility(View.GONE);


        holder.chapter.setText(page.getChapter() + ". " + page.getChapterName());

        if (_translation &&  ( page.getTranslation()!=null && page.getTranslation().length()!=0))
            holder.translation.setText(page.getTranslation());
        else
            holder.translation.setVisibility(View.GONE);


        if (_purport && ( page.getPurport()!=null && page.getPurport().length()!=0) ) {
            String puport = page.getPurport().replace("¶", "¶\n");
            holder.purport.setText(puport);
        } else
            holder.purport.setVisibility(View.GONE);


    }


    public void setData(List<Level1_Pages> pages, boolean text, boolean purport, boolean trans, boolean syn) {
        l1Pages = pages;
        _text = text;
        _purport = purport;
        _translation = trans;
        _synonyms = syn;
        notifyDataSetChanged();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int pos = holder.getAdapterPosition() + 1;
        Log.d(TAG, "onViewAttachedToWindow: " + pos);
        myListener.onItemClick(pos);
        myListener.itemChanged(l1Pages.get(pos-1));
    }

    public interface ItemListener {
        void onItemClick(int currentPage);
        void itemChanged(Level1_Pages page);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView text, synonyms, translation, chapter, purport;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            text = itemView.findViewById(R.id.text);
            synonyms = itemView.findViewById(R.id.synonyms);
            chapter = itemView.findViewById(R.id.chapter);
            translation = itemView.findViewById(R.id.translation);
            purport = itemView.findViewById(R.id.purport);
        }
    }
}
