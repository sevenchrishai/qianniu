package com.hqbb.duanzi.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hqbb.duanzi.R;
import com.hqbb.duanzi.base.BaseActivity;
import com.hqbb.duanzi.util.Utils;

public class NewsDetailActivity extends BaseActivity {

    private TextView mTitle;
    private ImageView mBack;
    private ProgressBar mProgressBar;
    private Button mLoading;
    private RelativeLayout mLayoutError;
    private WebView mWebView;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==404) {//主页不存在
                mWebView.loadUrl(url);
                mWebView.setVisibility(View.VISIBLE);
                mLayoutError.setVisibility(View.GONE);
            }
        }
    };
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        initView();
    }

    private void initView() {
        url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");

        mTitle = (TextView) findViewById(R.id.tv_title);
        mTitle.setText(title);

        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebView != null && mWebView.canGoBack()) {
                    mWebView.goBack();
                } else {
                    finish();
                }
            }
        });

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mWebView = (WebView) findViewById(R.id.webView);
        mLoading = (Button) findViewById(R.id.btn_loading);
        mLayoutError = (RelativeLayout) findViewById(R.id.layout_error);
        mWebView.setVisibility(View.VISIBLE);
        mLayoutError.setVisibility(View.GONE);

        //声明WebSettings子类
        WebSettings webSettings = mWebView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new ProgressClient());
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);  //关闭webview中缓存
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setDomStorageEnabled(true);//这句话必须保留。。否则无法播放优酷视频网页。。其他的可以
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        if (!TextUtils.isEmpty(url)) {
            mWebView.loadUrl(url);
        }
    }

    // H5 WebView Client
    public class ProgressClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.contains("bilibili")) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(url));
                if (Utils.isInstall("com.uc.browser")) {
                    intent.setClassName("com.uc.browser","com.uc.browser.ActivityUpdate");
                }
                startActivity(intent);
                return true;
            }
            return super.shouldOverrideUrlLoading(view,url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
//            final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
//            String message = "SSL Certificate error.";
//            switch (error.getPrimaryError()) {
//                case SslError.SSL_UNTRUSTED:
//                    message = "The certificate authority is not trusted.";
//                    break;
//                case SslError.SSL_EXPIRED:
//                    message = "The certificate has expired.";
//                    break;
//                case SslError.SSL_IDMISMATCH:
//                    message = "The certificate Hostname mismatch.";
//                    break;
//                case SslError.SSL_NOTYETVALID:
//                    message = "The certificate is not yet valid.";
//                    break;
//            }
//            message += " Do you want to continue anyway?";
//
//            builder.setTitle("SSL Certificate Error");
//            builder.setMessage(message);
//            builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    handler.proceed();
//                }
//            });
//            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    handler.cancel();
//                }
//            });
//            final AlertDialog dialog = builder.create();
//            dialog.show();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            mWebView.setVisibility(View.GONE);
            mLayoutError.setVisibility(View.VISIBLE);
            mLoading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                        webView.reload();
                    Message msg = new Message();
                    msg.what = 404;
                    handler.sendMessage(msg);
                }
            });
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}
