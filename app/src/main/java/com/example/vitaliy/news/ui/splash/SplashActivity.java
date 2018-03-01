package com.example.vitaliy.news.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.vitaliy.news.MainActivity;
import com.example.vitaliy.news.R;

public class SplashActivity extends AppCompatActivity {
    ImageView image;
    Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        animate();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void animate() {
        image = findViewById(R.id.imgLogo);
        mAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.spin_animation);
        image.setAnimation(mAnimation);

    }
}
