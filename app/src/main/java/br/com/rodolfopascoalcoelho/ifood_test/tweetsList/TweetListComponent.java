package br.com.rodolfopascoalcoelho.ifood_test.tweetsList;

import dagger.Component;
import dagger.Subcomponent;

@Component(
        modules = {TweetListModule.class}
)
public interface TweetListComponent {
    void inject(TweetsListActivity activity);
}

