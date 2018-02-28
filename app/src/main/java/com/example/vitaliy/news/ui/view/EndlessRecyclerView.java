package com.example.vitaliy.news.ui.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Custom recycler view with pagination ability
 */
public abstract class EndlessRecyclerView extends RecyclerView.OnScrollListener {
    /**
     * pagination starts when reach COUNT_ELEMENT_IN_RECYCLER_VIEW - 2 element
     */
    private int visibleThreshold = 2;
    private int currentPage = 1;
    private int previousTotalItemCount = 0;
    private boolean isLoading = true;
    private final int startingPage = 1;
    private RecyclerView.LayoutManager layoutManager;

    public EndlessRecyclerView(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int totalItemCount = layoutManager.getItemCount();
        int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

        if(isLoading && (totalItemCount > previousTotalItemCount)){
            isLoading = false;
            previousTotalItemCount = totalItemCount;
        }

        if(!isLoading && (lastVisibleItemPosition + visibleThreshold)> totalItemCount &&
                recyclerView.getAdapter().getItemCount() > visibleThreshold){
            currentPage++;
            onLoadMore(currentPage, totalItemCount, recyclerView);
        }
    }

    public void reset(){
        this.currentPage = this.startingPage;
        this.previousTotalItemCount = 0;
        this.isLoading = true;
    }

    public abstract void onLoadMore(int page, int totalitemCount, RecyclerView view);


}