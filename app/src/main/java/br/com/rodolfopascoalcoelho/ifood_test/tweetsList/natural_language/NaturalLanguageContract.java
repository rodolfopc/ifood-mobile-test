package br.com.rodolfopascoalcoelho.ifood_test.tweetsList.NaturalLanguage;

import com.google.api.services.language.v1.model.Sentiment;
import com.twitter.sdk.android.core.models.Tweet;

import br.com.rodolfopascoalcoelho.ifood_test.tweetsList.TweetsListPresenterImpl;

public class NaturalLanguageContract {


    public interface NaturalLanguageInteractorCallback{
        void showSentiment(Sentiment sentiment);

        void getTweetSentimentFailure(String message);
    }

    public interface NaturalLanguageInteractor{

        void getTweetSentiment(NaturalLanguageContract.NaturalLanguageInteractorCallback callback, String text, String lang);
    }

}
