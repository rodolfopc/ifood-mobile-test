package br.com.rodolfopascoalcoelho.ifood_test.tweetsList;

import br.com.rodolfopascoalcoelho.ifood_test.tweetsList.NaturalLanguage.NaturalLanguageContract;
import br.com.rodolfopascoalcoelho.ifood_test.tweetsList.NaturalLanguage.NaturalLanguageInteractorSDK;
import br.com.rodolfopascoalcoelho.ifood_test.tweetsList.user_tweets.UserTweetsAPI;
import br.com.rodolfopascoalcoelho.ifood_test.tweetsList.user_tweets.UserTweetsContract;
import br.com.rodolfopascoalcoelho.ifood_test.twitterLogin.twitter.TwitterInteractorContract;
import br.com.rodolfopascoalcoelho.ifood_test.twitterLogin.twitter.TwitterIteractorImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class TweetListModule {
    private final TweetsListContracts.TweetsListView view;

    public TweetListModule(TweetsListContracts.TweetsListView view) {
        this.view = view;
    }

    @Provides
    TweetsListContracts.TweetsListView provideTweetsListView() {
        return this.view;
    }

    @Provides
    static TweetsListContracts.TweetsListPresenter provideTweetsListPresenter(TweetsListContracts.TweetsListView tweetsListView,
                                                                              NaturalLanguageContract.NaturalLanguageInteractor naturalLanguageInteractor,
                                                                              UserTweetsContract.UserTweetsInteractor userTweetsInteractor,
                                                                              TwitterInteractorContract.TwitterInteractor twitterInteractor) {
        return new TweetsListPresenterImpl(tweetsListView,naturalLanguageInteractor,userTweetsInteractor,twitterInteractor);
    }

    @Provides
    static NaturalLanguageContract.NaturalLanguageInteractor provideNaturalLanguageInteractor(){
        return new NaturalLanguageInteractorSDK();
    }

    @Provides
    static TwitterInteractorContract.TwitterInteractor  provideTwitterInteractor (){
        return new TwitterIteractorImpl();
    }

    @Provides
    static UserTweetsContract.UserTweetsInteractor provideUserTweetsInteractor(){
        return new UserTweetsAPI();
    }

}
