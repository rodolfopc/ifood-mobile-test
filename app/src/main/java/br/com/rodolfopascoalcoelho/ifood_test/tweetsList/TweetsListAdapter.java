package br.com.rodolfopascoalcoelho.ifood_test.tweetsList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.twitter.sdk.android.core.models.Tweet;
import java.util.List;

import br.com.rodolfopascoalcoelho.ifood_test.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TweetsListAdapter extends BaseAdapter {

    List<Tweet> tweets;
    public TweetsListAdapter(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public int getCount() {
        return tweets.size();
    }

    @Override
    public Object getItem(int position) {
        return tweets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        TweetViewHolder holder;

        if( convertView == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.tweet_item, parent, false);
            holder = new TweetViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (TweetViewHolder) view.getTag();
        }

        Tweet tweet = (Tweet) getItem(position);

        holder.tweetText.setText(tweet.text);

        return view;
    }


    static class TweetViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tweet_text)
        TextView tweetText;

        public TweetViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



}
