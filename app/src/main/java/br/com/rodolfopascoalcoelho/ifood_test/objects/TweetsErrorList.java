package br.com.rodolfopascoalcoelho.ifood_test.objects;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.util.List;

public class TweetsErrorList {

    @Expose
    List<TweetError> errors;

    public List<TweetError> getErrors() {
        return errors;
    }

    public class TweetError {
        @Expose
        private int code;
        @Expose
        private String message;

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
