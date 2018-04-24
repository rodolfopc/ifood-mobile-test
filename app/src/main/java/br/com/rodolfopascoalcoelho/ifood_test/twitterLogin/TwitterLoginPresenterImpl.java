package br.com.rodolfopascoalcoelho.ifood_test.twitterLogin;

import android.app.Activity;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.rodolfopascoalcoelho.ifood_test.twitterLogin.twitter.TwitterInteractorContract;

public class TwitterLoginPresenterImpl implements TwitterLoginContracts.TwitterLoginPresenter, TwitterInteractorContract.TwitterLoginCallback {


    @Inject
    @Named("TwitterLogin")
    TwitterInteractorContract.TwitterInteractor twitterInteractor;

    @Inject
    TwitterLoginContracts.TwitterLoginView twitterLoginView;

    public TwitterLoginPresenterImpl(TwitterLoginContracts.TwitterLoginView twitterLoginView, TwitterInteractorContract.TwitterInteractor twitterInteractor) {
        this.twitterLoginView = twitterLoginView;
        this.twitterInteractor = twitterInteractor;
    }

    public void twitterLogin(Activity activity){
        twitterInteractor.doLogin(activity,this);

    }

    public void onActivityResult(int requestCode, int responseCode, Intent intent) {
        twitterInteractor.onActivityResult(requestCode, responseCode, intent);
    }

    @Override
    public void checkTwitterLoginSession() {
        twitterInteractor.checkTwitterLoginSession(this);
    }


    @Override
    public void loginError(String message) {
        twitterLoginView.showErrorDialog(message);
    }

    @Override
    public void loginSuccess() {
        twitterLoginView.showLoginSuccess();
    }
}
