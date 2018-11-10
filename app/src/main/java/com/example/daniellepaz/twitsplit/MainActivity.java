package com.example.daniellepaz.twitsplit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.daniellepaz.twitsplit.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.daniellepaz.twitsplit.TwitSplitUtils.processTweets;

public class MainActivity extends AppCompatActivity {

    private EditText mInputBox;
    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInputBox = findViewById(R.id.editText);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                processInput(mInputBox.getText().toString());
            }
        });


    }

    /**
     * Processes Tweet from user and posts the appropriate output
     * @param tweet
     */

    private void processInput(String tweet) {

        String [] processedTweets = processTweets(tweet);
        for( int i = 0; i <= processedTweets.length - 1; i++)
        {
            System.out.println(processedTweets[i] + ", Length: " + processedTweets[i].length());
            if (processedTweets[i].length() == 50) {
                System.out.println("TALO");
            }
        }

        List<String> processedTweetsList = Arrays.asList(processedTweets);

        System.out.println("ArrayLength: " + processedTweets.length);
        System.out.println("InputString length: " + tweet.length());


        // Create the adapter to convert the array to views
        TweetsAdapter adapter = new TweetsAdapter(this, processedTweetsList);
        // Attach the adapter to a ListView
        ListView listView = findViewById(R.id.tweets_listview);
        listView.setAdapter(adapter);
        mInputBox.setVisibility(View.GONE);
        findViewById(R.id.button).setVisibility(View.GONE);

    }


}
