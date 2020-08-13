package com.bhaktivedanta_library.books.ui.bookmarks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bhaktivedanta_library.books.R;
import com.bhaktivedanta_library.books.dao.bookmarks.Bookmarks;

import java.util.ArrayList;
import java.util.List;

class BookmarksAdapter extends RecyclerView.Adapter<BookmarksAdapter.ViewHolder> {
    BookmarksAdapter.ItemListener itemListener;
    private List<Bookmarks> bookmarks;

    public BookmarksAdapter(ItemListener itemListener) {
        bookmarks = new ArrayList<>();
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public BookmarksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookmarksAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookmark_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarksAdapter.ViewHolder holder, int position) {

        Bookmarks bookmark = bookmarks.get(position);
        holder.bookName.setText(bookmark.getBookName());

        if (bookmark.getLevel() == 1)
            holder.lastLayer.setText(bookmark.getChapter());
        else
            holder.lastLayer.setText(bookmark.getVerseName());


        holder.card.setOnClickListener(view -> itemListener.itemClicked(bookmark, view));


    }

    @Override
    public int getItemCount() {
        return bookmarks.size();
    }

    public void setData(List<Bookmarks> data) {
        bookmarks = data;
        notifyDataSetChanged();
    }

    public void setListener(BookmarksAdapter.ItemListener listener) {
        itemListener = listener;
    }

    public interface ItemListener {
        void itemClicked(Bookmarks bookmark, View v);
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
