package com.example.book.ui.l1Books;

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
import com.example.book.dao.lookup.LookupTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class L1Adapter extends RecyclerView.Adapter<L1Adapter.ViewHolder> {


    List<Integer> images;

    Map<String, Integer> map;
    int curPage;
    private L1Adapter.ItemListener myListener;
    private List<Level1_Pages> l1Pages;

    public L1Adapter(L1Adapter.ItemListener listener) {
        myListener = listener;
        l1Pages = new ArrayList<>();
    }
   private static final String TAG = "L1Adapter";


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

        Log.d(TAG, "scrolled to "+position);
        holder.text.setText(page.getText());
        holder.synonyms.setText(page.getSynonyms());
        holder.chapter.setText(page.getChapter()+". "+page.getChapterName());
        myListener.onItemClick(page.getPageNumber());

    }


    public void setData( List<Level1_Pages> pages) {
        l1Pages = pages;
        notifyDataSetChanged();
    }

    public interface ItemListener {
        void onItemClick(int currentPage);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView text,synonyms,translation,chapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
            synonyms = itemView.findViewById(R.id.synonyms);
            chapter = itemView.findViewById(R.id.chapter);
        }
    }
}
