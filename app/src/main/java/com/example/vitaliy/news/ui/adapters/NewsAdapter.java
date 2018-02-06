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
import com.example.vitaliy.news.data.model.news.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_shevchyk on 15.01.18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final static int TOP_POST = 0;
    private final static int OTHER_POSTS = 1;
    private onNewsClickListener mListener;
    private Context mContext;
    private List<Article> newsList = new ArrayList<>();
    private View view;


    public interface onNewsClickListener {
        void OnClick(Article article);
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TOP_POST;
            default:
                return OTHER_POSTS;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int id = 0;
        switch (viewType) {
            case TOP_POST:
                id = R.layout.news_item;
                break;
            case OTHER_POSTS:
                id = R.layout.news_items;
                break;
        }
        view = LayoutInflater.from(parent.getContext()).inflate(id, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.click(newsList.get(position), mListener);
        holder.bind(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void setOnItemClickListener(onNewsClickListener listener) {
        mListener = listener;
    }


    public void setData(List<Article> news, FragmentActivity activity) {
        newsList.clear();
        notifyDataSetChanged();
        newsList.addAll(news);
        mContext = activity;


        Log.e("newsAdapter", "news size = " + news.size());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title, description;
        ImageView newsPicture;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardViewItem);
            title = itemView.findViewById(R.id.newsTitleTV);
            description = itemView.findViewById(R.id.newsDescriptionTV);
            newsPicture = itemView.findViewById(R.id.newsImgView);
        }

        void bind(Article news) {
            title.setText(news.getTitle());
            title.setTypeface(null, Typeface.BOLD);
            description.setText(news.getDescription());
            description.setTypeface(null, Typeface.ITALIC);
            Glide.with(mContext)
                    .load(news.getUrlToImage())
                    .error(R.drawable.n_ff)
                    .centerCrop()
                    .into(newsPicture);
        }

        public void click(final Article article, final onNewsClickListener onNewsClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onNewsClickListener.OnClick(article);
                }
            });
        }
    }
}
