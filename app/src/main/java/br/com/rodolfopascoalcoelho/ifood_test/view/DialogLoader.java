package br.com.rodolfopascoalcoelho.ifood_test.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

import br.com.rodolfopascoalcoelho.ifood_test.R;


/**
 * Created by rodolfopascoalcoelho on 18/03/18.
 */

public class DialogLoader extends Dialog {
    Activity activity;

    public DialogLoader(Activity activity){
        super(activity);
        this.activity = activity;
    }
    public DialogLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_loader);
    }
}
