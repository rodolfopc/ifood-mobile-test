package br.com.rodolfopascoalcoelho.ifood_test.twitterLogin;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import br.com.rodolfopascoalcoelho.ifood_test.R;
import br.com.rodolfopascoalcoelho.ifood_test.tweetsList.BaseActivity;
import br.com.rodolfopascoalcoelho.ifood_test.tweetsList.TweetsListActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class TwitterLoginActivity extends BaseActivity implements TwitterLoginContracts.TwitterLoginView{

    @Inject
    TwitterLoginContracts.TwitterLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_login);

        DaggerTwitterLoginComponent.builder()
                .twitterLoginModule(new TwitterLoginModule(this))
                .build().inject(this);

        ButterKnife.bind(this);

        presenter.checkTwitterLoginSession();
    }

    @OnClick(R.id.button_login) void login(){
        presenter.twitterLogin(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showLoginSuccess() {
        Intent intent = new Intent(getApplicationContext(), TweetsListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
