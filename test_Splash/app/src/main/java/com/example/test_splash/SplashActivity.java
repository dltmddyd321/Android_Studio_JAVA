package com.example.test_splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.style.ScaleXSpan;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv = findViewById(R.id.sp_tv);
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.spin);
        tv.setAnimation(anim);

        Handler hd = new Handler(); //Handler 선언
        hd.postDelayed(new SplashHandler(),3000);
        //3초 후에 SplashHandler 동작
    }

    private class SplashHandler implements Runnable {
        @Override
        public void run() {
            startActivity(new Intent(getApplication(),MainActivity.class));
            SplashActivity.this.finish();
        }
    }
}