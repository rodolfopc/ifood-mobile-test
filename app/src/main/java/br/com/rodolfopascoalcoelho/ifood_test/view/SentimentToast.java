package br.com.rodolfopascoalcoelho.ifood_test.view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.rodolfopascoalcoelho.ifood_test.R;

public class SentimentToast extends Toast {
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public SentimentToast(Context context,String emoji,String sentimentName,int color) {
        super(context);
        View layout = LayoutInflater.from(context).inflate(R.layout.custom_toast,null);

        TextView text_emoji_toast = layout.findViewById(R.id.text_emoji_toast);
        text_emoji_toast.setText(emoji);

        TextView text = layout.findViewById(R.id.text_toast);
        text.setText(sentimentName);

        layout.setBackgroundColor(context.getResources().getColor(color));

        setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        setDuration(Toast.LENGTH_SHORT);
        setView(layout);
    }


}
