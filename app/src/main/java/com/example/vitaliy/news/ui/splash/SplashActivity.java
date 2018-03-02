package com.example.vitaliy.news.ui.splash;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.vitaliy.news.MainActivity;
import com.example.vitaliy.news.R;

public class SplashActivity extends AppCompatActivity {
   private ImageView image;
   private Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        animate();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_anim, R.anim.slide_out_anim);
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
    }

    private void animate() {
        image = findViewById(R.id.imgLogo);
        mAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.spin_animation);
        image.setAnimation(mAnimation);

    }
}
