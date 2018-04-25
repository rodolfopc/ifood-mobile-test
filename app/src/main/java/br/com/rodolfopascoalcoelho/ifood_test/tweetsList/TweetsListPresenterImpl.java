package br.com.rodolfopascoalcoelho.ifood_test.tweetsList;

import android.widget.EditText;

import com.google.api.services.language.v1.model.Sentiment;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import javax.inject.Inject;

import br.com.rodolfopascoalcoelho.ifood_test.R;
import br.com.rodolfopascoalcoelho.ifood_test.tweetsList.NaturalLanguage.NaturalLanguageContract;
import br.com.rodolfopascoalcoelho.ifood_test.tweetsList.user_tweets.UserTweetsContract;
import br.com.rodolfopascoalcoelho.ifood_test.twitterLogin.twitter.TwitterInteractorContract;

public class TweetsListPresenterImpl implements TweetsListContracts.TweetsListPresenter, NaturalLanguageContract.NaturalLanguageInteractorCallback, UserTweetsContract.UserTweetsInteractorCallback, TwitterInteractorContract.TwitterLogoutCallback{

    @Inject
    TweetsListContracts.TweetsListView tweetsListView;

    @Inject
    NaturalLanguageContract.NaturalLanguageInteractor naturalLanguageInteractor;

    @Inject
    TwitterInteractorContract.TwitterInteractor twitterInteractor;

    @Inject
    UserTweetsContract.UserTweetsInteractor userTweetsInteractor;

    public TweetsListPresenterImpl(TweetsListContracts.TweetsListView tweetsListView,
                                   NaturalLanguageContract.NaturalLanguageInteractor naturalLanguageInteractor,
                                   UserTweetsContract.UserTweetsInteractor userTweetsInteractor,
                                   TwitterInteractorContract.TwitterInteractor twitterInteractor) {
        this.tweetsListView = tweetsListView;
        this.naturalLanguageInteractor = naturalLanguageInteractor;
        this.userTweetsInteractor = userTweetsInteractor;
        this.twitterInteractor = twitterInteractor;
    }

    @Override
    public void getSentiment(Tweet tweet) {
        tweetsListView.showLoader(true);
        naturalLanguageInteractor.getTweetSentiment(this,tweet.text,tweet.lang);
    }

    @Override
    public void getTweets(String userName) {
        tweetsListView.showLoader(true);
        userTweetsInteractor.getTweets(this, userName);
    }

    public boolean validateFields(EditText userNameField){
        boolean isValid = true;
        if (userNameField.getText().toString().trim().isEmpty()) {
            userNameField.setError("This field cannot be empty");
            isValid = false;
        }
        else if (userNameField.getText().toString().trim().contains(" ")) {
            userNameField.setError("No Spaces Allowed");
            isValid = false;
        }
        return isValid;
    }


    @Override
    public void logout() {
        twitterInteractor.doLogout(this);
    }

    @Override
    public void showSentiment(Sentiment sentiment) {
        tweetsListView.showLoader(false);
        SentimentEnum sentimentEnum = SentimentEnum.getSentimentByScore(sentiment.getScore());
        tweetsListView.show(new String(Character.toChars(sentimentEnum.emoji)),sentimentEnum.name(),sentimentEnum.color);
    }

    @Override
    public void getTweetSentimentFailure(String message) {
        tweetsListView.showLoader(false);
        tweetsListView.showErrorDialog(message);
    }

    @Override
    public void fetchTweets(List<Tweet> items) {
        tweetsListView.showLoader(false);
        if(items.size()>0) {
            tweetsListView.fetchUserTweets(items);
        }else{
            tweetsListView.showEmptyView();
        }
    }

    @Override
    public void fetchTweetsFailure(String message) {
        tweetsListView.showLoader(false);
        tweetsListView.showErrorDialog(message);
    }

    @Override
    public void logoutError(String message) {

    }

    @Override
    public void logoutSuccess() {
        tweetsListView.logoutSuccess();
    }

    enum SentimentEnum{
        SAD(0x1F614,R.color.blue,-1.0,-0.25),
        NEUTRAL(0x1F610,R.color.gray,-0.25,0.25),
        HAPPY(0x1F603, R.color.yellow,0.25,1.0);

        int emoji;
        int color;
        double minRange;
        double maxRange;

        SentimentEnum(int emoji, int color, double minRange, double maxRange) {
            this.emoji = emoji;
            this.color = color;
            this.minRange = minRange;
            this.maxRange = maxRange;
        }

        public static SentimentEnum getSentimentByScore(double score){
            for (SentimentEnum sentiment: SentimentEnum.values()) {
                if(score>sentiment.minRange && score<=sentiment.maxRange){
                    return sentiment;
                }
            }
            return null;
        }
    }
}
