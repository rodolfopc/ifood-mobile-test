package br.com.rodolfopascoalcoelho.ifood_test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;

import br.com.rodolfopascoalcoelho.ifood_test.R;
import br.com.rodolfopascoalcoelho.ifood_test.view.DialogLoader;

public abstract class BaseActivity extends AppCompatActivity {

    public void showErrorDialog(String message) {
        final AlertDialog.Builder dialog =  new AlertDialog.Builder(this);
        dialog.setTitle(R.string.error);
        dialog.setMessage(message);
        dialog.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        dialog.show();
    }



    DialogLoader dialogLoader;
    public void showLoader(final boolean isShow) {
        if (dialogLoader == null)
            dialogLoader = new DialogLoader(this);
        if (isShow) {
            dialogLoader.show();
        } else {
            dialogLoader.dismiss();
        }
    }
}
