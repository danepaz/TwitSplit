package com.example.daniellepaz.twitsplit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TweetsAdapter extends ArrayAdapter<String> {

    public TweetsAdapter(Context context, List<String> tweets) {
        super(context, 0, tweets);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String tweet = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tweet_item, parent, false);
        }
        // Lookup view for data population
        TextView tweetTextView = convertView.findViewById(R.id.tweet);

        // Populate the data into the template view using the data object
        tweetTextView.setText(tweet);

        // Return the completed view to render on screen
        return convertView;
    }

}
