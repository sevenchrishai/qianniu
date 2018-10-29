package com.hqbb.duanzi.retrofit2;

import com.hqbb.duanzi.BuildConfig;
import com.hqbb.duanzi.base.AppApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ylh on 2018/1/9 0009.
 */

public class RetrofitUtil {

    public static AppInterface mInstance;


    public static synchronized AppInterface getInstance() {
        if (mInstance == null) {
            initHttp();
        }
        return mInstance;
    }

    private static void initHttp() {
        //OkHttpClient
        if (AppApplication.okHttpClient == null) {
            //网络请求拦截器
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG) {
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            }else {
                interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            }
            AppApplication.okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(35, TimeUnit.SECONDS)
                    //.addNetworkInterceptor(mTokenInterceptor)
                    //.authenticator(mAuthenticator)
                    .addInterceptor(interceptor)
                    .build();
        }
        //Retrofit
        mInstance = AppApplication.retrofit.create(AppInterface.class);

    }

}
