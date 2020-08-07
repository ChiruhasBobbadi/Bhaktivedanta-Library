package com.example.book.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book.Model.SearchModel;
import com.example.book.R;

import java.util.ArrayList;
import java.util.List;

class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    SearchAdapter.ItemListener itemListener;
    private List<SearchModel> models;

    public SearchAdapter(SearchAdapter.ItemListener itemListener) {
        models = new ArrayList<>();
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookmark_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {

        SearchModel model = models.get(position);
        holder.bookName.setText(model.getBookName());


            holder.lastLayer.setText(model.getLastLevelName());


        holder.card.setOnClickListener(view -> itemListener.itemClicked(model, view));


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setData(List<SearchModel> data) {
        models = data;
        notifyDataSetChanged();
    }

    public void setListener(SearchAdapter.ItemListener listener) {
        itemListener = listener;
    }

    public interface ItemListener {
        void itemClicked(SearchModel bookmark, View v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookName, lastLayer;
        ConstraintLayout card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.bookName);
            lastLayer = itemView.findViewById(R.id.lastLayer);
            card = itemView.findViewById(R.id.layout);
        }
    }
}