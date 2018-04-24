package br.com.rodolfopascoalcoelho.ifood_test.twitterLogin;

import br.com.rodolfopascoalcoelho.ifood_test.twitterLogin.twitter.TwitterInteractorContract;
import br.com.rodolfopascoalcoelho.ifood_test.twitterLogin.twitter.TwitterIteractorImpl;
import dagger.Module;
import dagger.Provides;

@Module
public class TwitterLoginModule {
    private final TwitterLoginContracts.TwitterLoginView view;


    public TwitterLoginModule(TwitterLoginContracts.TwitterLoginView view) {
        this.view = view;
    }

    @Provides
    TwitterLoginContracts.TwitterLoginView provideTwitterLoginView() {
        return this.view;
    }


    @Provides
    static TwitterLoginContracts.TwitterLoginPresenter provideTwitterLoginPresenter(TwitterLoginContracts.TwitterLoginView twitterLoginView,
                                                                                  TwitterInteractorContract.TwitterInteractor twitterInteractor) {
        return new TwitterLoginPresenterImpl(twitterLoginView,twitterInteractor);
    }

    @Provides
    static TwitterInteractorContract.TwitterInteractor  provideTwitterInteractor (){
        return new TwitterIteractorImpl();
    }



}
