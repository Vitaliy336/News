package com.example.vitaliy.news.ui.topnews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vitaliy.news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public class TopNewsAdapter extends RecyclerView.Adapter<TopNewsAdapter.NewsHolder>{

    List<String> data = new ArrayList<>();

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_category_item, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class NewsHolder extends RecyclerView.ViewHolder {
        Button button;

        public NewsHolder(View itemView) {
            super(itemView);
            button = (Button)itemView.findViewById(R.id.button);
        }
        void bind(String str){

        }
    }
}
