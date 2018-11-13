package com.example.daniellepaz.twitsplit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.Arrays;
import java.util.List;

import static com.example.daniellepaz.twitsplit.TwitSplitUtils.processTweets;

public class MainActivity extends AppCompatActivity {

    private EditText mInputBox;
    private Button mPostButton;
    private Button mBackButton;
    private ListView mTweetsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInputBox = findViewById(R.id.input_tweet);
        mPostButton =  findViewById(R.id.post_button);
        mBackButton = findViewById(R.id.back_button);
        mTweetsList = findViewById(R.id.tweets_listview);
        mTweetsList.setVisibility(View.GONE);
        mBackButton.setVisibility(View.GONE);

        mPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processInput(mInputBox.getText().toString());
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackButton.setVisibility(View.GONE);
                mTweetsList.setVisibility(View.GONE);
                mPostButton.setVisibility(View.VISIBLE);
                mInputBox.setText("");
                mInputBox.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * Processes Tweet from user and posts the appropriate output
     * @param tweet
     */

    private void processInput(String tweet) {

        //as per requirement, the return should be a String Array
        String [] processedTweets = processTweets(tweet);
        List<String> processedTweetsList = Arrays.asList(processedTweets);
        displayOutput(processedTweetsList);
    }

    /**
     * Display proper output to user
     */
    private void displayOutput(List processedTweetsList) {

        if (!processedTweetsList.isEmpty()) {
            if (processedTweetsList.get(0).equals(TwitSplitUtils.ERROR_NOWHITESPACE)) {
                mInputBox.setError(TwitSplitUtils.ERROR_NOWHITESPACE_MESSAGE);
            } else if (processedTweetsList.get(0).equals(TwitSplitUtils.ERROR_EMPTYTWEET)) {
                mInputBox.setError(TwitSplitUtils.ERROR_EMPTYTWEET_MESSAGE);
            } else {
                // Create the adapter to convert the array to views
                TweetsAdapter adapter = new TweetsAdapter(this, processedTweetsList);
                // Attach the adapter to a ListView
                ListView listView = findViewById(R.id.tweets_listview);
                listView.setAdapter(adapter);
                listView.setVisibility(View.VISIBLE);
                mBackButton.setVisibility(View.VISIBLE);
                mInputBox.setVisibility(View.GONE);
                mPostButton.setVisibility(View.GONE);
            }
        } else {
            mInputBox.setError(TwitSplitUtils.ERROR_EMPTYTWEET_MESSAGE);
        }
    }
}
