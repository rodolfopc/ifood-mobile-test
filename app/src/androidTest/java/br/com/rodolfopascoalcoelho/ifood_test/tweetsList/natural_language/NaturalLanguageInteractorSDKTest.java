package br.com.rodolfopascoalcoelho.ifood_test.tweetsList.NaturalLanguage;

import com.google.api.services.language.v1.model.Sentiment;
import com.twitter.sdk.android.core.models.Tweet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import br.com.rodolfopascoalcoelho.ifood_test.tweetsList.DaggerTweetListComponent;
import br.com.rodolfopascoalcoelho.ifood_test.tweetsList.TweetListComponent;
import br.com.rodolfopascoalcoelho.ifood_test.tweetsList.TweetListModule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NaturalLanguageInteractorSDKTest {

    NaturalLanguageInteractorSDK naturalLanguageInteractorSDK;

    @Captor
    private ArgumentCaptor<NaturalLanguageContract.NaturalLanguageInteractorCallback> naturalLanguageArgumentCaptor;

    @Mock
    NaturalLanguageContract.NaturalLanguageInteractorCallback naturalLanguageInteractorCallback;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        naturalLanguageInteractorSDK = new NaturalLanguageInteractorSDK();
    }

    @Test
    public void getTweetSentiment() {
        naturalLanguageInteractorSDK.getTweetSentiment(naturalLanguageInteractorCallback,"","pt");
        verify(naturalLanguageInteractorCallback).getTweetSentimentFailure("Language not accepted");
    }
}