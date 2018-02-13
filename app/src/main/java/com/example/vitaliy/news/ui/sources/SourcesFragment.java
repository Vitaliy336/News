package com.example.vitaliy.news.ui.sources;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vitaliy.news.MainActivity;
import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.model.source.Source;
import com.example.vitaliy.news.data.NewsDataRepository;
import com.example.vitaliy.news.ui.adapters.CategoriesAdapter;
import com.example.vitaliy.news.ui.adapters.SourcesAdapter;

import java.util.Arrays;
import java.util.List;


public class SourcesFragment extends Fragment implements SourcesContract.ISourcesView {
    private View rootView;
    private SourcesPresenter presenter;
    private RecyclerView sourcesRV, categoriesS;
    private SourcesAdapter sourcesAdapter;
    private CategoriesAdapter sCategoriesAdapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sources_news_fragment, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        initListener();
        initPresenter();
        updateData();

    }

    private void updateData() {
        presenter.start();
    }

    private void initPresenter() {
        presenter = new SourcesPresenter();
        presenter.attachView(this);

    }

    private void initListener() {
        sCategoriesAdapter.setCategoryItemClick(new CategoriesAdapter.onCategoryItemClick() {
            @Override
            public void onCatClick(String str) {
                presenter.getSourceCategory(str);
                presenter.prepareSources();
            }
        });

        sourcesAdapter.setSourceItemClickListener(new SourcesAdapter.onSourceClickListener() {
            @Override
            public void onClick(Source source) {
                ((MainActivity) getActivity()).showTopNews(source.getId());
            }
        });
    }

    private void initView() {
        sCategoriesAdapter = new CategoriesAdapter();
        sourcesAdapter = new SourcesAdapter();
        sCategoriesAdapter.setData(Arrays.asList(rootView.getResources().getStringArray(R.array.ctList)));

        LinearLayoutManager categoryLM = new LinearLayoutManager(getActivity());
        LinearLayoutManager sourcesLM = new LinearLayoutManager(getActivity());

        categoryLM.setOrientation(LinearLayoutManager.HORIZONTAL);
        sourcesLM.setOrientation(LinearLayoutManager.VERTICAL);

        sourcesRV = rootView.findViewById(R.id.sourcesRV);
        categoriesS = rootView.findViewById(R.id.categoriesS);
        sourcesRV.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        sourcesRV.setLayoutManager(sourcesLM);
        sourcesRV.setAdapter(sourcesAdapter);

        categoriesS.setLayoutManager(categoryLM);
        categoriesS.setAdapter(sCategoriesAdapter);
    }


    @Override
    public void showSources(List<Source> list) {
        sourcesAdapter.setInfo(list, getActivity());
        Log.e("SSS", list.size() + "");
    }

}
