package com.example.vitaliy.news;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public interface MainMVP {
    interface view{
        void displayToastMessage();
    }
    interface presentor{
        void clickedbyText();
    }
}
