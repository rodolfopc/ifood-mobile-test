package br.com.rodolfopascoalcoelho.ifood_test.tweetsList.user_tweets;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.Date;

public class UserTweetsSDK implements UserTweetsContract.UserTweetsInteractor {


    @Override
    public void getTweets(final UserTweetsContract.UserTweetsInteractorCallback callback, String userName) {



        UserTimeline userTimeline = new UserTimeline.Builder().screenName(userName).maxItemsPerRequest(20).build();
        userTimeline.next(new Date().getTime(),new Callback<TimelineResult<Tweet>>() {
            @Override
            public void success(Result<TimelineResult<Tweet>> result) {
                if(result.data!=null && result.data.items!=null){
                    callback.fetchTweets(result.data.items);
                }
            }

            @Override
            public void failure(TwitterException exception) {
                callback.fetchTweetsFailure(exception.getMessage());
            }
        });
    }
}
