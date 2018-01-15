package com.example.vitaliy.news.ui.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vitaliy.news.R;
import com.example.vitaliy.news.ui.Model.newsModel.NewsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_shevchyk on 15.01.18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    List<NewsModel> newsList = new ArrayList<>();

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

    public void setData(List<NewsModel> news){
        newsList.clear();
        newsList.addAll(news);
        notifyDataSetChanged();
        Log.e("newsAdapter", "news size = "+news.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView title, description;
        ImageView newsPicture;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardViewItem);
            title = (TextView)itemView.findViewById(R.id.newsTitleTV);
            description = (TextView)itemView.findViewById(R.id.newsDescriptionTV);
            newsPicture = (ImageView)itemView.findViewById(R.id.newsImgView);
        }

        void bind(NewsModel news){
            title.setText(news.getTitle());
            description.setText(news.getDescription());
        }
    }
}
