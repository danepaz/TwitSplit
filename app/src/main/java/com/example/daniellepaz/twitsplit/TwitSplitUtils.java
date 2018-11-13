package com.example.daniellepaz.twitsplit;

import java.util.ArrayList;
import java.util.List;

public class TwitSplitUtils {

    public static String ERROR_NOWHITESPACE = "ERROR_NOWHITESPACE";
    public static String ERROR_EMPTYTWEET = "ERROR_EMPTYTWEET";
    public static String ERROR_NOWHITESPACE_MESSAGE = "Input invalid. Please put spaces on your tweet.";
    public static String ERROR_EMPTYTWEET_MESSAGE = "Empty tweet. Type something...";


    public static String[] processTweets(String tweet) {

        List<String> splittedTweets = splitTweets(tweet);
        splittedTweets = addParts(splittedTweets);

        String[] arraySplittedTweets = new String[splittedTweets.size()];
        arraySplittedTweets = splittedTweets.toArray(arraySplittedTweets);
        return arraySplittedTweets;
    }

    /**
     * Method to split the tweets with the given requirement of:
     * 1. If a user's input is less than or equal to 50 characters, leave it as is
     * 2. If a user's input is greater than 50 characters, split it into chunks that
     * each is less than or equal to 50 characters and post each chunk as a separate
     * message.
     * 3. If the message contains a span of non-whitespace characters
     * longer than 50 characters, treat as an error
     */
    private static List<String> splitTweets(String tweet) {

        List<String> splitTweet = new ArrayList<>();
        String holder;
        tweet = tweet.trim().replaceAll("\\s+", " ");

        //if tweet's length is 50 or less than 50, return as is
        if (tweet.isEmpty() || tweet.length() == 0) {
            splitTweet.add(ERROR_EMPTYTWEET);
        } else if (tweet.length() <= 50) {
            splitTweet.add(tweet);
        } else {
            //if tweet is a series of non-whitespace characters
            if (!tweet.contains((" "))) {
                splitTweet.add(ERROR_NOWHITESPACE);
            } else {
                int possibleLines = String.valueOf(getPossibleLines(tweet)).length();
                //spaceForMessage computes the possible spaces that the part string (e.g. 1/2) would take up in a string
                //by getting the total number of lines possible, counting its' whole number, multiplying it by 2 and add 2
                //E.g. Total possible lines = 300 (maximum space to be taken is 8 since '300/300 ' has a length of 8
                int spaceForMessage = 50 - ((possibleLines * 2) + 2);
                int index = 0;
                while (index < tweet.length()) {
                    holder = tweet.substring(index, Math.min(index + spaceForMessage,tweet.length()));
                    if (holder.endsWith(" ")) {
                        splitTweet.add(holder.substring(0, holder.length()-1));
                        index += spaceForMessage;

                    } else if (index + spaceForMessage > tweet.length()) {
                        splitTweet.add(holder);
                        index += spaceForMessage;
                    } else {
                        int i;
                        for ( i = holder.length()-1; i >=0; i--) {
                            if (Character.toString(holder.charAt(i)).equals(" ")) {
                                break;
                            }
                        }
                        holder = tweet.substring(index, Math.min(index + i,tweet.length()-1));
                        splitTweet.add(holder);
                        index += i + 1;
                    }
                }
            }

        }
        return splitTweet;
    }

    /**
     * Method call to come up with the total number of possible parts/lines that a tweet can produce.
     * This already includes the partition string (e.g. 1/2, 2/2)
     *
     */

    private static int getPossibleLines(String tweet) {
        boolean match = false;
        int tweetLength = tweet.length();
        //computes initial possible lines available by dividing tweet length by 50
        int initialPossibleLines = (tweetLength / 50) + (tweetLength % 50 == 0 ? 0 : 1);
        //computes how much space is needed when the partition string is added
        //E.g. if initialPossibleLines = 300, then space needed for partition string is 8 since '300/300 ' has a length of 8
        int partitionStringLength = (String.valueOf(initialPossibleLines).length() * 2) + 2;
        //sum of all characters to be generated (tweet +
        int tweetAndPartSum = tweetLength + (partitionStringLength * initialPossibleLines);
        //computes final possible lines available by dividing tweetAndPartSum length by 50
        int finalValue = (tweetAndPartSum / 50) + (tweetAndPartSum % 50 == 0 ? 0 : 1);

        //computation above is lacking, do the same thing until the two values are equal
        if (partitionStringLength != ((String.valueOf(finalValue).length() * 2) + 2)) {
            while (!match) {

                partitionStringLength = (String.valueOf(finalValue).length() * 2) + 2;
                tweetAndPartSum = tweetLength + (partitionStringLength * initialPossibleLines);
                finalValue = (tweetAndPartSum / 50) + (tweetAndPartSum % 50 == 0 ? 0 : 1);

                if (partitionStringLength == ((String.valueOf(finalValue).length() * 2) + 2)) {
                    match = true;
                }
            }
        }
        return finalValue;
    }

    /**
     * Method to add the partition string (e.g. 1/2, 2/2) to the splitted tweets. Based on the requirements:
     *
     * "Split messages will have a "part indicator" appended to the beginning of
     * each section. In the example above, the message was split into two chunks, so
     * the part indicators read "1/2" and "2/2". Be aware that these count toward the
     * character limit."
     *
     */
    private static List<String> addParts(List<String> tweets) {

        if (tweets.size() > 1) {
            for( int i = 0; i <= tweets.size() - 1; i++) {
                tweets.set(i, String.valueOf(i + 1) + "/" + String.valueOf(tweets.size()) + " " + tweets.get(i));
            }
        }
        return tweets;
    }
}
