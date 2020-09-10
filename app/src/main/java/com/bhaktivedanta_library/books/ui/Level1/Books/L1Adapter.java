package com.bhaktivedanta_library.books.ui.Level1.Books;

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
import com.bhaktivedanta_library.books.dao.level1.pages.Level1_Pages;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

public class L1Adapter extends RecyclerView.Adapter<L1Adapter.ViewHolder> {


    private static final String TAG = "L1Adapter";

    boolean _text, _synonyms, _translation, _purport;
    Context context;
    String _font;
    private L1Adapter.ItemListener myListener;
    private List<Level1_Pages> l1Pages;
    private String searchKey;
    List<String> tags;
    List<String> list_text=new ArrayList<>();
    FlexboxLayout container;
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

        ViewHolder holder =  new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyler_view_base, parent, false));

        return holder;
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

        //holder.text.setText(page.getChapter() + ". " + page.getChapterName());
        holder.setData(page);


    }

    public void setTags(List<String> tags){

        notifyDataSetChanged();
    }


    public void setData(List<Level1_Pages> pages, boolean text, boolean purport, boolean trans, boolean syn,String font,String search) {
        l1Pages = pages;
        _text = text;
        _purport = purport;
        _translation = trans;
        _synonyms = syn;
        _font=font;
        searchKey = search;
        Log.d(TAG, "setData: "+searchKey);
        notifyDataSetChanged();
    }

    /* public void setTags(List<String> tagList) {
        for (int index = 0; index < tagList.size(); index++) {
            final String tagName = tagList.get(index);
            final Chip chip = new Chip(context);
            int paddingDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 10,
                    context.getResources().getDisplayMetrics()
            );
            chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
            chip.setText(tagName);
            chip.setCloseIconResource(R.drawable.close);
            chip.setCloseIconEnabled(true);
            //Added click listener on close icon to remove tag from ChipGroup
            chip.setOnCloseIconClickListener(v -> {
                tagList.remove(tagName);
                chipGroup.removeView(chip);

            });

            chipGroup.addView(chip);
        }
    }*/

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int pos = holder.getAdapterPosition() + 1;
        Log.d(TAG, "onViewAttachedToWindow: " + pos);
        myListener.onPageChange(pos);
        myListener.itemChanged(l1Pages.get(pos - 1));
    }

    public interface ItemListener {
        void onPageChange(int current_page);
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


            adapter.setData(context,page, _text, _synonyms, _purport, _translation,_font,searchKey);
            rv.setAdapter(adapter);
        }

    }


}