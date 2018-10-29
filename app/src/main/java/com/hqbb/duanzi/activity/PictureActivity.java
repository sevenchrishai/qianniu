package com.hqbb.duanzi.activity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.hqbb.duanzi.R;
import com.hqbb.duanzi.base.BaseActivity;
import com.hqbb.duanzi.widget.glide.MyglideImageView;

import org.w3c.dom.Text;

import java.io.File;

import static com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView.ZOOM_FOCUS_CENTER_IMMEDIATE;

public class PictureActivity extends BaseActivity {

    private PhotoView mPhotoView;
    private SubsamplingScaleImageView imageView;
    private ImageView mBack;
    private ProgressBar mProgressBar;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        initView();
    }

    private void initView() {
        final String img_url = getIntent().getStringExtra("img_url");
        String title = getIntent().getStringExtra("title");
//        Log.e("TAG", img_url);

        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTitle = findViewById(R.id.tv_title);
        mTitle.setText(title);

        imageView = (SubsamplingScaleImageView) findViewById(R.id.long_iv);
        imageView.setMaxScale(15);
        imageView.setZoomEnabled(true);
        imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        mPhotoView = (PhotoView) findViewById(R.id.photoView);
        //  启用图片缩放功能
        mPhotoView.enable();
        mPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        if (img_url.contains("gif")) {
            mPhotoView.setVisibility(View.VISIBLE);
            MyglideImageView.setImgAsGif(this, mPhotoView, img_url, mProgressBar);
        } else {
//            MyglideImageView.setImgfit(this, mPhotoView, img_url);
            mPhotoView.setVisibility(View.GONE);
            Glide.with(this)
                .load(img_url)
                .downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                        // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
                        ImageSource imageSource = ImageSource.uri(Uri.fromFile(resource));
                        int sWidth = BitmapFactory.decodeFile(resource.getAbsolutePath()).getWidth();
                        int sHeight = BitmapFactory.decodeFile(resource.getAbsolutePath()).getHeight();
                        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
//                        int width = wm.getDefaultDisplay().getWidth();
                        int height = wm.getDefaultDisplay().getHeight();
//                        float scale = SystemUtil.displaySize.x / (float) sWidth;
                        //float centerX = SystemUtil.displaySize.x / 2;
                        // imageView.setImage(ImageSource.uri(Uri.fromFile(resource)), new ImageViewState(2.0F, new PointF(0, 0), 0));
                        //imageView.setImage(ImageSource.uri(Uri.fromFile(resource)), new ImageViewState(2.0F, new PointF(0, 0), 0));
                        if (sHeight >= height
                                && sHeight / sWidth >=3) {
                            imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
                            imageView.setImage(ImageSource.uri(Uri.fromFile(resource)), new ImageViewState(2.0F, new PointF(0, 0), 0));
                        }else {
                            imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
                            imageView.setImage(ImageSource.uri(Uri.fromFile(resource)));
                            imageView.setDoubleTapZoomStyle(ZOOM_FOCUS_CENTER_IMMEDIATE);
                        }
                        mProgressBar.setVisibility(View.GONE);
                    }
                });

        }

    }

}
