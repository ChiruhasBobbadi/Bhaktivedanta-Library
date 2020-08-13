package com.bhaktivedanta_library.books.ui.Level2.books;

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
import com.bhaktivedanta_library.books.dao.level2.pages.Level2_Pages;

import java.util.ArrayList;
import java.util.List;

class L2Adapter extends RecyclerView.Adapter<L2Adapter.ViewHolder> {

    private static final String TAG = "L2Adapter";
    boolean _text, _synonyms, _translation, _purport;
    Context context;
    String _font;
    private L2Adapter.ItemListener myListener;
    private List<Level2_Pages> l2Pages;
    private String searchTerm;

    public L2Adapter(L2Adapter.ItemListener listener, Context con) {
        myListener = listener;
        context = con;
        l2Pages = new ArrayList<>();
    }

    @NonNull
    @Override
    public L2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new L2Adapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyler_view_base, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull L2Adapter.ViewHolder holder, int position) {
        //todo
        Vibrator v = (Vibrator) holder.itemView.getContext().getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 20 milliseconds
        v.vibrate(20);

        Level2_Pages page = l2Pages.get(position);

        holder.text.setText(page.getVerseName()+"");
        holder.setData(page);

    }

    @Override
    public int getItemCount() {
        return l2Pages.size();
    }

    public void setData(List<Level2_Pages> pages, boolean text, boolean purport, boolean trans, boolean syn,String font,String search) {
        l2Pages = pages;
        _text = text;
        _purport = purport;
        _translation = trans;
        _synonyms = syn;
        _font=font;
        searchTerm = search;
        notifyDataSetChanged();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull L2Adapter.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int pos = holder.getAdapterPosition() + 1;
        Log.d(TAG, "onViewAttachedToWindow: " + pos);
        myListener.onPageChange(pos);
        myListener.itemChanged(l2Pages.get(pos - 1));
    }


    public interface ItemListener {
        void onPageChange(int currentPage);

        void itemChanged(Level2_Pages page);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        L2PurportAdapter adapter;
        RecyclerView rv;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
            text = itemView.findViewById(R.id.text);
            rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            adapter = new L2PurportAdapter();

        }

        public void setData(Level2_Pages page) {

            adapter.setData(context,page, _text, _synonyms, _purport, _translation,_font,searchTerm);
            rv.setAdapter(adapter);
        }

    }
}
