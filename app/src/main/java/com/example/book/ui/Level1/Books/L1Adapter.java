package com.example.book.ui.Level1.Books;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book.R;
import com.example.book.dao.level1.pages.Level1_Pages;

import java.util.ArrayList;
import java.util.List;

public class L1Adapter extends RecyclerView.Adapter<L1Adapter.ViewHolder> {


    private static final String TAG = "L1Adapter";

    boolean _text, _synonyms, _translation, _purport;
    Context context;
    private L1Adapter.ItemListener myListener;
    private List<Level1_Pages> l1Pages;

    public L1Adapter(L1Adapter.ItemListener listener, Context con) {
        myListener = listener;
        context = con;
        l1Pages = new ArrayList<>();
    }

    public void setListener(L1Adapter.ItemListener listener) {
        myListener = listener;
    }


    @Override
    public L1Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyler_view_base, parent, false));
    }

    @Override
    public int getItemCount() {
        return l1Pages.size();
    }

    @Override
    public void onBindViewHolder(L1Adapter.ViewHolder holder, int position) {
        //todo
        Vibrator v = (Vibrator) holder.itemView.getContext().getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 20 milliseconds
        v.vibrate(20);

        Level1_Pages page = l1Pages.get(position);

        holder.setData(page);


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
        myListener.onPageChange(pos);
        myListener.itemChanged(l1Pages.get(pos - 1));
    }

    public interface ItemListener {
        void onPageChange(int currentPage);

        void itemChanged(Level1_Pages page);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        PurportAdapter adapter;
        RecyclerView rv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
            rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            adapter = new PurportAdapter();

        }

        public void setData(Level1_Pages page) {


            adapter.setData(page, _text, _synonyms, _purport, _translation);
            rv.setAdapter(adapter);
        }

    }


}
