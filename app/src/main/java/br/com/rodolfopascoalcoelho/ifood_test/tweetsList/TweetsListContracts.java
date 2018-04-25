package br.com.rodolfopascoalcoelho.ifood_test.tweetsList;

import android.widget.EditText;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

public interface TweetsListContracts {

    interface TweetsListView {
        void show(String emoji, String sentimentName, int color);

        void fetchUserTweets(List<Tweet> items);

        void showErrorDialog(String message);

        void logoutSuccess();

        void showLoader(final boolean isShow);

        void showEmptyView();
    }

    interface TweetsListPresenter {
        void getSentiment(Tweet tweet);

        void getTweets(String userName);

        boolean validateFields(EditText userNameField);

        void logout();
    }

}
