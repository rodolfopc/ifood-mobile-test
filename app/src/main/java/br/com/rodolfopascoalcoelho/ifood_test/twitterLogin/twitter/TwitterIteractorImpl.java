package br.com.rodolfopascoalcoelho.ifood_test.twitterLogin.twitter;

import android.app.Activity;
import android.content.Intent;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

public class TwitterIteractorImpl implements TwitterInteractorContract.TwitterInteractor {


    TwitterAuthClient mTwitterAuthClient;

    public TwitterIteractorImpl() {
        this.mTwitterAuthClient = new TwitterAuthClient();
    }

    @Override
    public void doLogin(Activity activity, final TwitterInteractorContract.TwitterLoginCallback callback) {
        mTwitterAuthClient.authorize(activity, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> twitterSessionResult) {
                callback.loginSuccess();
            }

            @Override
            public void failure(TwitterException e) {
                e.printStackTrace();
                callback.loginError(e.getMessage());
            }
        });
    }

    @Override
    public void doLogout(TwitterInteractorContract.TwitterLogoutCallback callback) {
        TwitterCore.getInstance().getSessionManager().clearActiveSession();
        callback.logoutSuccess();
    }


    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent intent) {
        mTwitterAuthClient.onActivityResult(requestCode, responseCode, intent);
    }

    @Override
    public void checkTwitterLoginSession(TwitterInteractorContract.TwitterLoginCallback callback) {
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if(session!=null && session.getAuthToken()!=null && session.getAuthToken().token!=null) {
            callback.loginSuccess();
        }
    }


}
