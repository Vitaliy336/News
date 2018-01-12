package com.example.vitaliy.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vitaliy on 1/11/2018.
 */

public class TopNewsFragment extends Fragment implements MainMVP.view{

    TextView text;
    Presentor presentor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presentor = new Presentor(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.top_news_fragment, container, false);
        text = (TextView) rootView.findViewById(R.id.HotTv);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presentor.clickedbyText();
            }
        });


        return rootView;
    }

    @Override
    public void displayToastMessage() {
        Toast.makeText(getActivity(), "Finaly done?", Toast.LENGTH_SHORT).show();
    }
}
