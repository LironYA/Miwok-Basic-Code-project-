package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter <Word> {
    private int mColorResourceId;
    private static final String LOG_TAG = WordAdapter.class.getSimpleName();

    public WordAdapter(@NonNull Context context, ArrayList<Word> WordsMiEng, int colorResourceId) {
        super(context, 0, WordsMiEng);
        mColorResourceId = colorResourceId;
    }
    @NonNull
    @Override
    //this method is called when the list view  is trying to display a list of items at a given position
    //convertView - a potential view that we could reuse
    //ViewGroup parent - refers to the parent of all the list items, in this case it's the ListView itself
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
// Check if the existing view is being reused, otherwise inflate the view
        if (listItemView == null) {
            //We inflate the view meaning we create a new list item layout
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Get the object located at this position in the list
        Word currentWord = getItem(position);
        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView EnglishText = (TextView) listItemView.findViewById(R.id.English_list);
        // Get the English word from the current Word object and set this text on the name TextView
        EnglishText.setText(currentWord.getDefaultTranslation());
        //Find the TextView in the list_item.xml layout with the Miwok word
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.MiWok_list);
        miwokTextView.setText(currentWord.getMiwokTranslation());
        //Return the whole list item layout so that it can be shown in the ListView
        ImageView miwok_image = (ImageView) listItemView.findViewById(R.id.MiWok_image);

        if(currentWord.hadImage()){
            miwok_image.setImageResource(currentWord.getImageResourceId());
            miwok_image.setVisibility(View.VISIBLE);
        } else {
            miwok_image.setVisibility(View.GONE);
        }
        //Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        //find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        //Set the GB color of the text container view
        textContainer.setBackgroundColor(color);
        return listItemView;

    }
}