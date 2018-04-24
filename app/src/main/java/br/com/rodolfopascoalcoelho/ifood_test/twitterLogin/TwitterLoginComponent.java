package br.com.rodolfopascoalcoelho.ifood_test.twitterLogin;

import dagger.Component;

@Component(
        modules = {TwitterLoginModule.class}
)
public interface TwitterLoginComponent {
    void inject(TwitterLoginActivity activity);
}

