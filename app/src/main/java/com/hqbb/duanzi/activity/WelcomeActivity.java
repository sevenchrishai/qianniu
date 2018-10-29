package com.hqbb.duanzi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.base.BaseActivity;
import com.hqbb.duanzi.widget.glide.MyglideImageView;

public class WelcomeActivity extends BaseActivity {

    private ImageView imageView;

    private Runnable delayJumpRunnable;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();

        initTimer();

    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.iv_wel);
        MyglideImageView.setLocalImg(mContext, imageView, R.mipmap.ic_wel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                handler.removeCallbacks(delayJumpRunnable);
                finish();
            }
        });

    }

    private void initTimer() {
        delayJumpRunnable = getDelayJumpRunnable();
        handler = new Handler();
        handler.postDelayed(delayJumpRunnable, 1500);//延迟2秒
    }

    @NonNull
    private Runnable getDelayJumpRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }

        };
    }

}
