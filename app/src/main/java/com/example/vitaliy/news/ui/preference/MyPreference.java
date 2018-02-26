package com.example.vitaliy.news.ui.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.ContextThemeWrapper;

import com.example.vitaliy.news.R;

public class MyPreference extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
        setDefault();

    }


    public static class MyPreferenceFragment extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref1);

        }
    }
    private void setDefault() {
        PreferenceManager.setDefaultValues(this, R.xml.pref1, true);
    }
}
