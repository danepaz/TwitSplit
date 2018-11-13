package com.example.daniellepaz.twitsplit;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TwitSplitUtilsTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /**
     * Tests the use case wherein the tweet is no longer needed to be split
     */
    @Test
    public void processTweets_isCorrect_Single() {

        String[] expectedTweet = {"I can't believe Tweeter now supports chunking"};
        String[] actualTweet = TwitSplitUtils.processTweets("I can't believe Tweeter now supports chunking");

        //check content
        assertArrayEquals(expectedTweet, actualTweet);
        //check length of array
        assertEquals(expectedTweet.length, actualTweet.length);
    }

    /**
     * Tests the use case wherein the tweet is long enough to be split.
     */
    @Test
    public void processTweets_isCorrect_Multiple() {

        String[] expectedTweet = {"1/2 I can't believe Tweeter now supports chunking", "2/2 my messages, so I don't have to do it myself."};
        String[] actualTweet = TwitSplitUtils.processTweets("I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself.");

        //check content
        assertArrayEquals(expectedTweet, actualTweet);
        //check length of array
        assertEquals(expectedTweet.length, actualTweet.length);
        //check length of each array item if less than of equal to 50
        boolean check = false;
        for (int i = 0; i <=1; i++) {
            if (actualTweet[i].length() <=50) {
                check = true;
            }

            assertEquals(true, check);
            check = false;
        }
    }

    /**
     * Tests the use case wherein the tweet doesn't have any spaces.
     */
    @Test
    public void processTweets_error_NoSpace() {

        String[] expectedTweet = {TwitSplitUtils.ERROR_NOWHITESPACE};
        String[] actualTweet = TwitSplitUtils.processTweets("Ican'tbelieveTweeternowsupportschunkingmymessagessoIdon'thavetodoitmyself.");

        //check content
        assertArrayEquals(expectedTweet, actualTweet);
        //check length of array
        assertEquals(expectedTweet.length, actualTweet.length);
    }

    /**
     * Tests the use case wherein the tweet doesn't have any spaces.
     */
    @Test
    public void processTweets_error_Empty() {

        String[] expectedTweet = {TwitSplitUtils.ERROR_EMPTYTWEET};
        String[] actualTweet = TwitSplitUtils.processTweets("");

        //check content
        assertArrayEquals(expectedTweet, actualTweet);
        //check length of array
        assertEquals(expectedTweet.length, actualTweet.length);
    }

}