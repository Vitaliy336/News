package com.example.vitaliy.news.ui.sources;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vitaliy.news.MainActivity;
import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;
import com.example.vitaliy.news.data.sourceModel.Source;
import com.example.vitaliy.news.ui.ActivityCallBack;
import com.example.vitaliy.news.ui.ViewPagerAdapter;
import com.example.vitaliy.news.ui.adapters.CategoriesAdapter;
import com.example.vitaliy.news.ui.adapters.SourcesAdapter;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by v_shevchyk on 19.01.18.
 */

public class SourcesFragment extends Fragment implements SourcesContract.ISourcesView{
    private View rootView;
    private ActivityCallBack callBack;
    private SourcesPresenter presenter;
    private RemoteNewsDataSource dataSource;
    private RecyclerView sourcesRV, categoriesS;
    private SharedPreferences sharedPreferences;
    private SourcesAdapter sourcesAdapter;
    private CategoriesAdapter sCategoriesAdapter;
    private final String SAVED_TEXT = "sourceID";


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBack = (ActivityCallBack)context;
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
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                presenter.getSourceCategory(str);
                presenter.prepareSources();

            }
        });

        sourcesAdapter.setSourceItemClickListener(new SourcesAdapter.onSourceClickListener() {
            @Override
            public void onClick(Source source) {
                callBack.sendSourceID(source.getId());
                ViewPager viewPager = getActivity().findViewById(R.id.viewpager);
                viewPager.setCurrentItem(0);
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

}
