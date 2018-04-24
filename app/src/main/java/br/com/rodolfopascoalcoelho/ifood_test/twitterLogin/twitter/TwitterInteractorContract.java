package br.com.rodolfopascoalcoelho.ifood_test.twitterLogin.twitter;

import android.app.Activity;
import android.content.Intent;

public class TwitterInteractorContract {

    public interface TwitterLoginCallback {

        void loginError(String message);

        void loginSuccess();
    }

    public interface TwitterInteractor {

        void doLogin(Activity activity, final TwitterLoginCallback callback);

        void doLogout(final TwitterLogoutCallback callback);

        void onActivityResult(int requestCode, int responseCode, Intent intent);

        void checkTwitterLoginSession(final TwitterLoginCallback callback);
    }

    public interface TwitterLogoutCallback {

        void logoutError(String message);

        void logoutSuccess();
    }


}
