package br.com.rodolfopascoalcoelho.ifood_test.twitterLogin;

import android.app.Activity;
import android.content.Intent;

public interface TwitterLoginContracts {

    interface TwitterLoginView {

        void showErrorDialog(String message);

        void showLoginSuccess();
    }

    interface TwitterLoginPresenter {

        void twitterLogin(Activity activity);

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void checkTwitterLoginSession();
    }

}
