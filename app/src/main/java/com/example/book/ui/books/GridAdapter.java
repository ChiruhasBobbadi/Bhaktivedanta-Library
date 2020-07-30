package com.example.book.ui.books;

import android.graphics.drawable.Drawable;
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
import com.example.book.dao.lookup.LookupTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {


    private ItemListener myListener;
    List<Integer> images;
    List<LookupTable> lookupTables ;
     Map<String,Integer> map;

    public GridAdapter(ItemListener listener) {

        myListener = listener;

        initialise();
    }

    private void initialise() {
        lookupTables = new ArrayList<>();
        map = new HashMap<>();
        map.put("KṚṢṆA, The Supreme Personality of Godhead",R.drawable.a);
        map.put("The Nectar of Instruction",R.drawable.b);
        map.put("Śrī Īśopaniṣad",R.drawable.c);
        map.put("Kṛṣṇa Consciousness, The Topmost Yoga System",R.drawable.d);
        map.put("Rāja-Vidyā: The King of Knowledge",R.drawable.e);
        map.put("The Nectar of Devotion",R.drawable.f);
        map.put("Teachings of Lord Caitanya",R.drawable.g);
        map.put("Elevation to Kṛṣṇa Consciousness",R.drawable.h);
        map.put("Kṛṣṇa Consciousness, The Matchless Gift",R.drawable.i);
        map.put("Transcendental Teachings of Prahlāda Mahārāja",R.drawable.j);
        map.put("Teachings of Lord Kapila, the Son of Devahuti",R.drawable.k);
        map.put("Teachings of Queen Kuntī",R.drawable.l);
        map.put("The Path of Perfection",R.drawable.m);
        map.put("The Perfection of Yoga",R.drawable.n);
        map.put("Beyond Birth & Death",R.drawable.o);
        map.put("On the Way to Kṛṣṇa",R.drawable.p);
        map.put("Easy Journey to Other Planets",R.drawable.q);
        map.put("Perfect Questions, Perfect Answers",R.drawable.r);
        map.put("Kṛṣṇa, the Reservoir of Pleasure",R.drawable.s);
        map.put("Bhagavad-gītā As It Is",R.drawable.t);
        map.put("Life Comes from Life",R.drawable.u);
        map.put("The Science of Self Realization",R.drawable.v);
        map.put("Śrīmad-Bhāgavatam",R.drawable.w);
        map.put("Śrī Caitanya-caritāmṛta",R.drawable.x);
    }


    public void setListener(ItemListener listener) {
        myListener = listener;
    }


    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_layout,parent, false));
    }

    @Override
    public int getItemCount() {
        return lookupTables.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

       // String name = names.get(position);

      final  LookupTable look = lookupTables.get(position);

        holder.bookName.setText(look.getBookName());


        holder.bookImage.setImageResource(map.get(look.getBookName()));

        holder.card.setOnClickListener(view->myListener.onItemClick(look,view));



    }



    public void setData(List<LookupTable> lookupTables) {
        this.lookupTables = lookupTables;
        notifyDataSetChanged();
    }

    public interface ItemListener {
        void onItemClick(LookupTable clicked,View view);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookName;
        ImageView bookImage;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.bookName);
            bookImage = itemView.findViewById(R.id.bookImage);
            card = itemView.findViewById(R.id.card);
        }
    }


}