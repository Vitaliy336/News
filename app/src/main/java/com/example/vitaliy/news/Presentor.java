package com.example.vitaliy.news;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public class Presentor implements MainMVP.presentor {

    private final MainMVP.view view;

    Presentor(MainMVP.view view){
        this.view = view;
    }



    @Override
    public void clickedbyText() {
        view.displayToastMessage();
    }
}
