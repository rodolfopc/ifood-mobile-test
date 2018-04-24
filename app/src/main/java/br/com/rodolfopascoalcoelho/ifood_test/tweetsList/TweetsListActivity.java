package br.com.rodolfopascoalcoelho.ifood_test.tweetsList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.rodolfopascoalcoelho.ifood_test.R;
import br.com.rodolfopascoalcoelho.ifood_test.twitterLogin.TwitterLoginActivity;
import br.com.rodolfopascoalcoelho.ifood_test.view.SentimentToast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class TweetsListActivity extends BaseActivity implements TweetsListContracts.TweetsListView {

    @Inject
    TweetsListContracts.TweetsListPresenter presenter;

    @BindView(R.id.list_view)
    ListView list_view;

    @BindView(R.id.button_search)
    ImageButton button_search;

    @BindView(R.id.text_user_name)
    EditText text_user_name;

    List<Tweet> tweets = new ArrayList<>();
    TweetsListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerTweetListComponent.builder()
                .tweetListModule(new TweetListModule(this))
                .build().inject(this);

        ButterKnife.bind(this);

        adapter = new TweetsListAdapter(tweets);
        list_view.setAdapter(adapter);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tweet item = (Tweet) adapter.getItem(position);
                presenter.getSentiment(item);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_logout:
                presenter.logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void show(String emoji, String sentimentName, int color) {
        SentimentToast toast = new SentimentToast(getApplicationContext(),emoji,sentimentName,color);
        toast.show();
    }

    @Override
    public void fetchUserTweets(List<Tweet> items) {
        tweets.clear();
        tweets.addAll(items);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void logoutSuccess() {
        Intent intent = new Intent(getApplicationContext(), TwitterLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @OnClick(R.id.button_search) void searchUser(){
        if(presenter.validateFields(text_user_name)) {
            presenter.getTweets(text_user_name.getText().toString().trim());
            hideSoftKeyboard();
        }
    }



}
