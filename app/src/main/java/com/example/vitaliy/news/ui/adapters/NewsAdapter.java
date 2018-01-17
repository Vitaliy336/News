package com.example.vitaliy.news.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.model.Article;
import com.example.vitaliy.news.ui.topnews.TopNewsPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_shevchyk on 15.01.18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context mContext;
    List<Article> newsList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void setData(List<Article> news, FragmentActivity activity) {
        newsList.clear();
        newsList.addAll(news);
        notifyDataSetChanged();
        mContext = activity;
        Log.e("newsAdapter", "news size = "+news.size());
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cv;
        TextView title, description;
        ImageView newsPicture;
        TopNewsPresenter presenter;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardViewItem);
            title = (TextView)itemView.findViewById(R.id.newsTitleTV);
            description = (TextView)itemView.findViewById(R.id.newsDescriptionTV);
            newsPicture = (ImageView)itemView.findViewById(R.id.newsImgView);
        }

        void bind(Article news) {
            title.setText(news.getTitle());
            title.setTypeface(null, Typeface.BOLD);
            description.setText(news.getDescription());
            description.setTypeface(null, Typeface.ITALIC);
            Glide.with(mContext)
                    .load(news.getUrlToImage())
                    .override(300, 150)
                    .error(R.drawable.n_f)
                    .centerCrop()
                    .into(newsPicture);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
