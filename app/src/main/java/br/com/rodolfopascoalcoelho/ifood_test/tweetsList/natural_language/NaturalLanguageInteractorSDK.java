package br.com.rodolfopascoalcoelho.ifood_test.tweetsList.NaturalLanguage;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.services.language.v1.CloudNaturalLanguage;
import com.google.api.services.language.v1.CloudNaturalLanguageRequestInitializer;
import com.google.api.services.language.v1.model.AnnotateTextRequest;
import com.google.api.services.language.v1.model.AnnotateTextResponse;
import com.google.api.services.language.v1.model.Sentiment;

import java.io.IOException;

import static br.com.rodolfopascoalcoelho.ifood_test.tweetsList.NaturalLanguage.AnnotateTextRequestBuilder.FeaturesTypeEnum.DOCUMENTSENTIMENT;

public class NaturalLanguageInteractorSDK implements NaturalLanguageContract.NaturalLanguageInteractor {


    @Override
    public void getTweetSentiment(NaturalLanguageContract.NaturalLanguageInteractorCallback callback, String text, String lang) {
        AnnotateTextRequest request = null;
        try {
            request = new AnnotateTextRequestBuilder(text).setDocumentLanguage(lang).setFeatures(DOCUMENTSENTIMENT).build();
            NaturalLanguageAsyncTask asyncTask = new NaturalLanguageAsyncTask(request,callback);
            asyncTask.execute();
        } catch (AnnotateTextRequestBuilder.AcceptedLanguagesException e) {
            e.printStackTrace();
            callback.getTweetSentimentFailure(e.getMessage());
        }
    }

    public static class NaturalLanguageAsyncTask extends AsyncTask<Void, Void, AnnotateTextResponse>{
        CloudNaturalLanguage naturalLanguageService;
        AnnotateTextRequest request;
        NaturalLanguageContract.NaturalLanguageInteractorCallback callback;

        public NaturalLanguageAsyncTask(AnnotateTextRequest request, NaturalLanguageContract.NaturalLanguageInteractorCallback callback) {
            this.request = request;
            this.callback = callback;

            naturalLanguageService = new CloudNaturalLanguage.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),
                    null
            ).setCloudNaturalLanguageRequestInitializer(
                    new CloudNaturalLanguageRequestInitializer("AIzaSyBWHfxZjasedtutUr7mNwTEqI-jtmBEVqc")
            ).build();

        }

        @Override
        protected AnnotateTextResponse doInBackground(Void... voids) {
            AnnotateTextResponse response = null;
            try {
                response = naturalLanguageService.documents().annotateText(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(AnnotateTextResponse response) {
            super.onPostExecute(response);
            if (response != null) {
                Sentiment sent = response.getDocumentSentiment();
                callback.showSentiment(sent);
            }
        }

    }
}
