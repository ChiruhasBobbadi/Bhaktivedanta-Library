package com.bhaktivedanta_library.books.ui.Level3.Books;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhaktivedanta_library.books.R;
import com.bhaktivedanta_library.books.dao.level3.pages.Level3_Pages;

import java.util.ArrayList;
import java.util.List;

class L3Adapter extends RecyclerView.Adapter<L3Adapter.ViewHolder> {

    private static final String TAG = "L2Adapter";
    boolean _text, _synonyms, _translation, _purport;
    Context context;
    String _font;
    private L3Adapter.ItemListener myListener;
    private List<Level3_Pages> l3Pages;
    private String search;
    public L3Adapter(L3Adapter.ItemListener listener, Context con) {
        myListener = listener;
        context = con;
        l3Pages = new ArrayList<>();
    }

    @NonNull
    @Override
    public L3Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new L3Adapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyler_view_base, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull L3Adapter.ViewHolder holder, int position) {
        //todo
        Vibrator v = (Vibrator) holder.itemView.getContext().getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 20 milliseconds
        v.vibrate(20);

        Level3_Pages page = l3Pages.get(position);
        holder.text.setText(page.getVerse() + ". " + page.getVerseName());
        holder.setData(page);
    }

    public void setData(List<Level3_Pages> pages, boolean text, boolean purport, boolean trans, boolean syn, String font,String s) {
        l3Pages = pages;
        _text = text;
        _purport = purport;
        _translation = trans;
        _synonyms = syn;
        _font = font;
        search =s;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return l3Pages.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        int pos = holder.getAdapterPosition() + 1;
        Log.d(TAG, "onViewAttachedToWindow: " + pos);
        myListener.onPageChange(pos);
        myListener.itemChanged(l3Pages.get(pos - 1));
    }

    public interface ItemListener {
        void onPageChange(int currentPage);

        void itemChanged(Level3_Pages page);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        L3PurportAdapter adapter;
        RecyclerView rv;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
            rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            adapter = new L3PurportAdapter();
            text = itemView.findViewById(R.id.text);
        }

        public void setData(Level3_Pages page) {


            adapter.setData(context, page, _text, _synonyms, _purport, _translation, _font,search);
            rv.setAdapter(adapter);
        }

    }
}
