package com.bhaktivedanta_library.books.ui.Level1.Books;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhaktivedanta_library.books.R;
import com.bhaktivedanta_library.books.dao.level1.pages.Level1_Pages;

import java.util.Arrays;
import java.util.List;

public class PurportAdapter extends RecyclerView.Adapter {

    private static final String TAG = "PurportAdapter";
    Level1_Pages page;
    List<String> purport;
    String _font;
    Context context;

    boolean _text, _synonyms, _translation, _purport;
    String searchKey;

    public void setData(Context con, Level1_Pages data, boolean text, boolean syn, boolean pu, boolean trans, String font, String s) {
        _text = text;
        _synonyms = syn;
        _purport = pu;
        _translation = trans;
        page = data;
        _font = font;
        context = con;
        searchKey = s;
        String purp = page.getPurport().replace("¶", "\n");
        purport = Arrays.asList(purp.split("\\$"));

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == 0) {
            view = inflater.inflate(R.layout.page, parent, false);
            return new ViewHolderRest(view);
        } else if (viewType == 1) {
            view = inflater.inflate(R.layout.purport, parent, false);
            return new ViewHolderPurport(view);
        } else {
            view = inflater.inflate(R.layout.poem, parent, false);
            return new ViewHolderPoem(view);
        }


    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (position == 0) {

            ViewHolderRest rest = (ViewHolderRest) holder;

            switch (_font) {
                case "textDefault":
                    rest.text.setTextAppearance(android.R.style.TextAppearance_Medium);
                    rest.synonyms.setTextAppearance(android.R.style.TextAppearance_Medium);
                    rest.translation.setTextAppearance(android.R.style.TextAppearance_Large);
                    rest.title.setTextAppearance(android.R.style.TextAppearance_Large);
                    break;
                case "textSmall":
                    rest.text.setTextAppearance(android.R.style.TextAppearance_Small);
                    rest.synonyms.setTextAppearance(android.R.style.TextAppearance_Small);
                    rest.translation.setTextAppearance(android.R.style.TextAppearance_Small);
                    rest.title.setTextAppearance(android.R.style.TextAppearance_Small);
                    break;
                case "textMedium":
                    rest.text.setTextAppearance(android.R.style.TextAppearance_Medium);
                    rest.synonyms.setTextAppearance(android.R.style.TextAppearance_Medium);
                    rest.translation.setTextAppearance(android.R.style.TextAppearance_Medium);
                    rest.title.setTextAppearance(android.R.style.TextAppearance_Medium);
                    break;
                case "textLarge":
                    rest.text.setTextAppearance(android.R.style.TextAppearance_Large);
                    rest.synonyms.setTextAppearance(android.R.style.TextAppearance_Large);
                    rest.translation.setTextAppearance(android.R.style.TextAppearance_Large);
                    rest.title.setTextAppearance(android.R.style.TextAppearance_Large);
                    break;
                default:
                    rest.text.setTextAppearance(android.R.style.TextAppearance_Medium);
                    rest.synonyms.setTextAppearance(android.R.style.TextAppearance_Medium);
                    rest.translation.setTextAppearance(android.R.style.TextAppearance_Large);
                    rest.title.setTextAppearance(android.R.style.TextAppearance_Large);
                    break;
            }

            rest.text.setTextColor(context.getResources().getColor(R.color.text_color));
            rest.synonyms.setTextColor(context.getResources().getColor(R.color.text_color));
            rest.translation.setTextColor(context.getResources().getColor(R.color.text_color));
            rest.translation.setTypeface(Typeface.DEFAULT_BOLD);
            rest.title.setTextColor(context.getResources().getColor(R.color.text_color));
            rest.title.setTypeface(Typeface.DEFAULT_BOLD);

            if (_text && (page.getText() != null && page.getText().length() != 0))
                rest.text.setText("\n"+page.getText().trim());


            else
                rest.text.setVisibility(View.GONE);


            if (_synonyms && (page.getSynonyms() != null && page.getSynonyms().length() != 0))
                rest.synonyms.setText("\n"+page.getSynonyms().replace("¶", "").trim());
            else
                rest.synonyms.setVisibility(View.GONE);


            if (_translation && (page.getTranslation() != null && page.getTranslation().length() != 0))
            {
                if (searchKey != null) {
                    String s = page.getTranslation();
                    s = s.toLowerCase();
                    searchKey = searchKey.toLowerCase().trim();
                    int startIndex = s.indexOf(searchKey);
                    if (startIndex == -1) {
                        rest.translation.setText("\n"+page.getTranslation().replace("¶", "").trim());
                    } else {
                        SpannableString str = new SpannableString("\n"+page.getTranslation().trim());
                        str.setSpan(new BackgroundColorSpan(context.getResources().getColor(R.color.highlight)), startIndex, startIndex + searchKey.length(), 0);
                        rest.translation.setText(str);
                    }

                } else
                    rest.translation.setText("\n"+page.getTranslation().replace("¶", "").trim());
            } else
                rest.translation.setVisibility(View.GONE);

            rest.title.setText("\n"+page.getChapterName());

        }
        else if (position % 2 != 0) {
            ViewHolderPurport purp = (ViewHolderPurport) holder;
            switch (_font) {
                case "textDefault":
                    purp.purport.setTextAppearance(android.R.style.TextAppearance_Medium);
                    break;
                case "textSmall":
                    purp.purport.setTextAppearance(android.R.style.TextAppearance_Small);
                    break;
                case "textMedium":
                    purp.purport.setTextAppearance(android.R.style.TextAppearance_Medium);
                    break;
                case "textLarge":
                    purp.purport.setTextAppearance(android.R.style.TextAppearance_Large);
                    break;
                default:
                    purp.purport.setTextAppearance(android.R.style.TextAppearance_Medium);
                    break;
            }
            purp.purport.setTextColor(context.getResources().getColor(R.color.text_color));


            if (_purport && (page.getPurport() != null && page.getPurport().length() != 0)) {
                String r = purport.get(position - 1).replace("¶", "\n");
                if (searchKey != null) {
                    String s = purport.get(position - 1);
                    s = s.toLowerCase();
                    searchKey = searchKey.toLowerCase().trim();
                    int startIndex = s.indexOf(searchKey);
                    if (startIndex == -1)
                        purp.purport.setText(r);
                    else {
                        SpannableString str = new SpannableString(r);
                        str.setSpan(new BackgroundColorSpan(context.getResources().
                                getColor(R.color.highlight)), startIndex, startIndex + searchKey.length(), 0);
                        purp.purport.setText(str);
                    }

                } else
                    purp.purport.setText(r);

            } else
                purp.purport.setVisibility(View.GONE);
        }
        else {
            ViewHolderPoem poem = (ViewHolderPoem) holder;

            switch (_font) {
                case "textDefault":
                    poem.poem.setTextAppearance(android.R.style.TextAppearance_Medium);
                    break;
                case "textSmall":
                    poem.poem.setTextAppearance(android.R.style.TextAppearance_Small);
                    break;
                case "textMedium":
                    poem.poem.setTextAppearance(android.R.style.TextAppearance_Medium);
                    break;
                case "textLarge":
                    poem.poem.setTextAppearance(android.R.style.TextAppearance_Large);
                    break;
                default:
                    poem.poem.setTextAppearance(android.R.style.TextAppearance_Medium);
                    break;
            }
            poem.poem.setTextColor(context.getResources().getColor(R.color.text_color));
            poem.poem.setTypeface(null,Typeface.ITALIC);
            if (_purport && (page.getPurport() != null && page.getPurport().length() != 0)) {

                String t = purport.get(position - 1).trim();
                Log.d(TAG, "onBindViewHolder: "+t);
                poem.poem.setText("\n"+t.trim());
            } else
                poem.poem.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        else if (position % 2 != 0)
            return 1;
        else
            return 2;
    }

    @Override
    public int getItemCount() {
        return purport.size() + 1;
    }

    class ViewHolderRest extends RecyclerView.ViewHolder {
        TextView text, synonyms, translation,title;

        public ViewHolderRest(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            synonyms = itemView.findViewById(R.id.synonyms);
            translation = itemView.findViewById(R.id.translation);
            title = itemView.findViewById(R.id.title);
        }
    }

    class ViewHolderPurport extends RecyclerView.ViewHolder {
        TextView purport;

        public ViewHolderPurport(@NonNull View itemView) {
            super(itemView);
            purport = itemView.findViewById(R.id.purport);
        }
    }

    class ViewHolderPoem extends RecyclerView.ViewHolder {
        TextView poem;

        public ViewHolderPoem(@NonNull View itemView) {
            super(itemView);
            poem = itemView.findViewById(R.id.poem);
        }
    }
}