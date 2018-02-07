package com.example.vitaliy.news.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.model.source.Source;

import java.util.ArrayList;
import java.util.List;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.SourcesVH> {
    private List<Source> sources = new ArrayList<>();
    private onSourceClickListener sourceClickListener;
    private Context mContext;
    View view;

    public interface onSourceClickListener {
        void onClick(Source source);
    }

    public void setSourceItemClickListener(onSourceClickListener sourceItemClickListener) {
        this.sourceClickListener = sourceItemClickListener;
    }

    @Override
    public SourcesVH onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_item, parent, false);
        return new SourcesVH(view);
    }

    @Override
    public void onBindViewHolder(SourcesVH holder, int position) {
        holder.bind(sources.get(position));
        holder.sourceClick(sources.get(position), sourceClickListener);
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    public void setInfo(List<Source> sourceList, FragmentActivity activity) {
        sources.clear();
        sources.addAll(sourceList);
        mContext = activity;
        notifyDataSetChanged();

        Log.e("SourcesAdapter", "sources size = " + sources.size());
    }


    public class SourcesVH extends RecyclerView.ViewHolder {
        private TextView sourceName, sourceDescription;


        public SourcesVH(View itemView) {
            super(itemView);

            sourceName = itemView.findViewById(R.id.sourceName);
            sourceDescription = itemView.findViewById(R.id.sourceDescription);
        }

        void bind(Source source) {
            sourceName.setText(source.getName());
            sourceName.setTypeface(null, Typeface.BOLD);
            sourceDescription.setText(source.getDescription());
            sourceDescription.setTypeface(null, Typeface.ITALIC);
        }

        public void sourceClick(final Source source, final onSourceClickListener sourceClick) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sourceClick.onClick(source);
                }
            });
        }

    }
}
