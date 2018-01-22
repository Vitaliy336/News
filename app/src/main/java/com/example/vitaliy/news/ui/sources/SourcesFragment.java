package com.example.vitaliy.news.ui.sources;

import android.app.Activity;
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
import android.widget.Toast;

import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;
import com.example.vitaliy.news.data.sourceModel.Source;
import com.example.vitaliy.news.ui.adapters.CategoriesAdapter;
import com.example.vitaliy.news.ui.adapters.SourcesAdapter;

import java.util.List;

/**
 * Created by v_shevchyk on 19.01.18.
 */

public class SourcesFragment extends Fragment implements SourcesContract.ISourcesView{
    private View rootView;
    private OnSourceDataListener mSourceDataListener;
    private SourcesPresenter presenter;
    private RemoteNewsDataSource dataSource;
    private RecyclerView sourcesRV, categoriesS;
    private SourcesAdapter sourcesAdapter;
    private CategoriesAdapter sCategoriesAdapter;

    public interface OnSourceDataListener{
        public void onSourceDataReceived(String data);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mSourceDataListener = (OnSourceDataListener)activity
        }
        catch (Exception e){

        }
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

    }

    private void initPresenter() {
        dataSource = new RemoteNewsDataSource();
        presenter = new SourcesPresenter(dataSource);
        presenter.attachView(this);
        presenter.prepareCategories();
        presenter.prepareSources();


    }

    private void initListener() {
        sCategoriesAdapter.setCategoryItemClick(new CategoriesAdapter.onCategoryItemClick() {
            @Override
            public void onCatClick(String str) {
                presenter.prepareSourcesWithCategory(str);
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
            }
        });

        sourcesAdapter.setSourceItemClickListener(new SourcesAdapter.onSourceClickListener() {
            @Override
            public void onClick(Source source) {
                Toast.makeText(getActivity(), source.getName(), Toast.LENGTH_SHORT).show();

                mSourceDataListener.onSourceDataReceived(source.getName());//cc
//                ViewPager viewPager = getActivity().findViewById(R.id.viewpager);
//                viewPager.setCurrentItem(0);

//                TopNewsFragment topNewsFragment = new TopNewsFragment();
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.topNews, topNewsFragment).commit();
            }
        });
    }

    private void initView() {
        sCategoriesAdapter = new CategoriesAdapter();
        sourcesAdapter = new SourcesAdapter();

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
    public void displayCategories(List<String> categories) {
        sCategoriesAdapter.setData(categories);
    }

    @Override
    public void displayToastMessage() {

    }

    @Override
    public void showSources(List<Source> list) {
        sourcesAdapter.setInfo(list, getActivity());
        Log.e("SSS", list.size() + "");
    }

    @Override
    public void showSourcesWithCategory(List<Source> list) {
        sourcesAdapter.setInfo(list, getActivity());
    }
}
