package br.com.rodolfopascoalcoelho.ifood_test.tweetsList.user_tweets;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.network.OAuth1aInterceptor;
import com.twitter.sdk.android.core.models.Tweet;

import java.io.IOException;
import java.util.List;

import br.com.rodolfopascoalcoelho.ifood_test.objects.TweetsErrorList;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class UserTweetsAPI implements UserTweetsContract.UserTweetsInteractor {

    private Retrofit retrofit;

    public UserTweetsAPI() {
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        TwitterAuthConfig authConfig = TwitterCore.getInstance().getAuthConfig();

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        if(session!=null) {
            okHttpClient.addInterceptor(new OAuth1aInterceptor(session, authConfig));
        }

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.twitter.com/1.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();

    }

    public interface ApiInterface {

        @GET("statuses/user_timeline.json")
        Call<List<Tweet>> getUserDetails(@Query("screen_name") String screenName);

    }

    @Override
    public void getTweets(final UserTweetsContract.UserTweetsInteractorCallback callback, String userName) {
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<Tweet>> call = apiInterface.getUserDetails(userName);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                if(response.code()==200 && response.body()!=null) {
                    callback.fetchTweets(response.body());
                }else try {
                    if(response.errorBody()!=null){
                        String errorBodyString = response.errorBody().string();
                        if(errorBodyString!=null) {
                            TweetsErrorList errors = new Gson().fromJson(errorBodyString, TweetsErrorList.class);
                            if (errors != null && errors.getErrors()!=null && errors.getErrors().size() > 0) {
                                callback.fetchTweetsFailure(errors.getErrors().get(0).getMessage());
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {

            }
        });
    }
}
