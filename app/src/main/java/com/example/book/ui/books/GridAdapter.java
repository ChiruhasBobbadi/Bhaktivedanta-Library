package com.example.book.ui.books;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book.R;

import java.util.List;


public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {


    private ItemListener myListener;
    List<String> names;
    List<Integer> images;

    public GridAdapter(List<String> names) {
        this.names = names;

    }

    public GridAdapter(ItemListener listener) {

        myListener = listener;
    }

    public void setListener(ItemListener listener) {
        myListener = listener;
    }

/*    public void updateData(List<GodModel> GodModels)
    {
        this.GodModels= GodModels;

        notifyDataSetChanged();
    }*/

  /*  public List<GodModel> getData() {
        return GodModels;
    }*/



    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_layout,parent, false));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String name = names.get(position);

        holder.bookName.setText(name);


    }

    public interface ItemListener {
        void onItemClick();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookName;
        ImageView bookImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.bookName);
            bookImage = itemView.findViewById(R.id.bookImage);
        }
    }


}