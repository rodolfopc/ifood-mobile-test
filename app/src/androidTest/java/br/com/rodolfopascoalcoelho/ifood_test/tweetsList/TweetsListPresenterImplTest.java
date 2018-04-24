package br.com.rodolfopascoalcoelho.ifood_test.tweetsList;

import com.google.api.services.language.v1.model.Sentiment;
import com.twitter.sdk.android.core.models.Tweet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.rodolfopascoalcoelho.ifood_test.tweetsList.NaturalLanguage.NaturalLanguageContract;
import br.com.rodolfopascoalcoelho.ifood_test.tweetsList.user_tweets.UserTweetsContract;
import br.com.rodolfopascoalcoelho.ifood_test.twitterLogin.twitter.TwitterInteractorContract;

import static br.com.rodolfopascoalcoelho.ifood_test.tweetsList.TweetsListPresenterImpl.SentimentEnum.HAPPY;
import static br.com.rodolfopascoalcoelho.ifood_test.tweetsList.TweetsListPresenterImpl.SentimentEnum.NEUTRAL;
import static br.com.rodolfopascoalcoelho.ifood_test.tweetsList.TweetsListPresenterImpl.SentimentEnum.SAD;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TweetsListPresenterImplTest {
    @Mock
    TweetsListContracts.TweetsListView tweetsListView;

    @Mock
    NaturalLanguageContract.NaturalLanguageInteractor naturalLanguageInteractor;

    @Mock
    UserTweetsContract.UserTweetsInteractor userTweetsInteractor;

    @Mock
    TwitterInteractorContract.TwitterInteractor twitterInteractor;

    TweetsListPresenterImpl presenter;

    @Captor
    private ArgumentCaptor<UserTweetsContract.UserTweetsInteractorCallback> twitterArgumentCaptor;

    @Captor
    private ArgumentCaptor<NaturalLanguageContract.NaturalLanguageInteractorCallback> naturalLanguageArgumentCaptor;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new TweetsListPresenterImpl(tweetsListView, naturalLanguageInteractor, userTweetsInteractor, twitterInteractor);
    }

    @Test
    public void getSentiment() {
        Tweet tweet = mock(Tweet.class);
        presenter.getSentiment(tweet);
        verify(presenter.naturalLanguageInteractor).getTweetSentiment(naturalLanguageArgumentCaptor.capture(),eq(tweet.text),eq(tweet.lang));

    }

    @Test
    public void getTweets() {
        presenter.getTweets("gsuite");
        verify(presenter.userTweetsInteractor).getTweets(twitterArgumentCaptor.capture(),eq("gsuite"));
        twitterArgumentCaptor.getValue().fetchTweets(getList());
        ArgumentCaptor<List> entityArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(presenter.tweetsListView).fetchUserTweets(entityArgumentCaptor.capture());
        int value = entityArgumentCaptor.getValue().size();
        assertEquals(value,4);
    }

    @Test
    public void showSentiment() {

        presenter.showSentiment(new Sentiment().setScore(0.9f));
        verify(presenter.tweetsListView,times(1)).show(new String(Character.toChars(HAPPY.emoji)),HAPPY.name(),HAPPY.color);

        presenter.showSentiment(new Sentiment().setScore(0.0f));
        verify(presenter.tweetsListView,times(1)).show(new String(Character.toChars(NEUTRAL.emoji)),NEUTRAL.name(),NEUTRAL.color);

        presenter.showSentiment(new Sentiment().setScore(-0.9f));
        verify(presenter.tweetsListView,times(1)).show(new String(Character.toChars(SAD.emoji)),SAD.name(),SAD.color);

    }

    @Test
    public void getTweetSentimentFailure() {

    }

    @Test
    public void fetchTweets() {
    }

    @Test
    public void fetchTweetsFailure() {
    }


    private List<Tweet> getList() {
        ArrayList<Tweet> tweets = new ArrayList<>();
        tweets.add(mock(Tweet.class));
        tweets.add(mock(Tweet.class));
        tweets.add(mock(Tweet.class));
        tweets.add(mock(Tweet.class));
        return tweets;
    }
}