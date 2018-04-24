package br.com.rodolfopascoalcoelho.ifood_test;

import android.app.Application;
import android.content.res.Resources;

import java.util.logging.Logger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.support.DaggerApplication;

@Module
public class AppModule {
    iFoodTestApplication app;

    public AppModule(iFoodTestApplication application) {
        app = application;
    }

    @Provides
    @Singleton
    protected Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    protected Resources provideResources() {
        return app.getResources();
    }

}