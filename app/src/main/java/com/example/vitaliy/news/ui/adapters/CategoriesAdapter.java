package com.example.vitaliy.news.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vitaliy.news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitaliy on 1/14/2018.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private onCategoryItemClick onCategoryItemClick;
    List<String> data = new ArrayList<>();
    View view;

    public interface onCategoryItemClick{
        void onCatClick(String str);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onClick(data.get(position), onCategoryItemClick);
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setCategoryItemClick(onCategoryItemClick categoryItemClick){
        onCategoryItemClick = categoryItemClick;
    }

    public void setData(List<String> categories) {
        data.clear();
        data.addAll(categories);
        notifyDataSetChanged();
        Log.d("qweee", "size =" + getItemCount());
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView categoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryName =  itemView.findViewById(R.id.categoryName);
        }

        void bind(String str) {
            categoryName.setText(str);
        }

        public void onClick(final String str, final onCategoryItemClick onCategoryItemClick){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCategoryItemClick.onCatClick(str);
                }
            });
        }
    }
}
