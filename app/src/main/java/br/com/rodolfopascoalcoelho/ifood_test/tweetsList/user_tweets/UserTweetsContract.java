package br.com.rodolfopascoalcoelho.ifood_test.tweetsList.user_tweets;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

public class UserTweetsContract {

    public interface UserTweetsInteractorCallback{

        void fetchTweets(List<Tweet> items);

        void fetchTweetsFailure(String message);
    }

    public interface UserTweetsInteractor{
        void getTweets(UserTweetsInteractorCallback callback, String userName);
    }

}
