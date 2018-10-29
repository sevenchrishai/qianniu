package com.hqbb.duanzi.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.hqbb.duanzi.component.AppComponent;
import com.hqbb.duanzi.component.DaggerAppComponent;
import com.hqbb.duanzi.module.AppModule;
import com.hqbb.duanzi.module.BookApiModule;
import com.hqbb.duanzi.util.AppUtils;
import com.hqbb.duanzi.util.LogUtils;
import com.hqbb.duanzi.util.SharedPreferencesUtil;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hqbb.duanzi.base.AppApi.BASE_URL;

/**
 * Created by ylh on 2017/12/28 0028.
 */

public class AppApplication extends Application {

    /**
     * 管理Activity
     */
    private static HashMap<String, Activity> activityBuf = new HashMap<>();

    private static Context mContext;
    private static AppApplication sInstance;

    public static final String ALL_TYPE = "1";
    public static final String JOKE_TYPE = "2";
    public static final String PIC_TYPE = "3";
    public static final String VIDEO_TYPE = "4";

    public static final String PAGE_SIZE = "10";

    public static final String JD_APP_KEY = "846ff9e774b8cbbb29bf536d4a04b06d";

    public static Retrofit retrofit;

    public static OkHttpClient okHttpClient;
    private AppComponent appComponent;

    public static Context getmContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        sInstance = this;
        Basic.setAppContext(this);
        initCompoent();
        AppUtils.init(this);

        ZXingLibrary.initDisplayOpinion(this);

        initRetrofit();

        initPrefs();
        initNightMode();
    }

    public static AppApplication getsInstance() {
        return sInstance;
    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .bookApiModule(new BookApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

    protected void initNightMode() {
        boolean isNight = SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false);
        LogUtils.d("isNight=" + isNight);
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void initRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(35, TimeUnit.SECONDS)
                .connectTimeout(35, TimeUnit.SECONDS)
                .writeTimeout(35, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static String getKey(Activity activity) {
        return String.valueOf(activity.hashCode());
    }

    public static void addActivity(Activity activity) {
        if (activityBuf.get(getKey(activity)) == null) {
            activityBuf.put(getKey(activity), activity);
        }
    }

    public static void removeActivity(Activity activity) {
        if (activityBuf.get(getKey(activity)) != null) {
            activityBuf.remove(getKey(activity));
        }
    }

    public static void exitAllActivity() {
        ArrayList<Activity> removeList = new ArrayList<>(activityBuf.size());
        for (Iterator<Map.Entry<String, Activity>> iterator = activityBuf.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Activity> entry = iterator.next();
            removeList.add(entry.getValue());
        }
        exitActivityList(removeList);
        activityBuf.clear();
    }

    public static void exitAllActivityButOne(Activity activity) {
        ArrayList<Activity> removeList = new ArrayList<>(activityBuf.size());
        for (Iterator<Map.Entry<String, Activity>> iterator = activityBuf.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Activity> entry = iterator.next();
            if (!entry.getKey().equals(getKey(activity))) {
                removeList.add(entry.getValue());
            }
        }
        exitActivityList(removeList);
    }

    public static void exitActivityList(ArrayList<Activity> iActivities) {
        for (Activity activity : iActivities) {
            if (activity != null) {
                activity.finish();
            }
        }
        iActivities.clear();
    }

    public static HashMap<String, Activity> getActivityBuf() {
        return activityBuf;
    }
}
