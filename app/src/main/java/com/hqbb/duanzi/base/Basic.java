package com.hqbb.duanzi.base;

import android.app.Activity;
import android.content.res.Resources;

/**
 * 全局APP基类
 * @author chenyuzhong
 *
 */
public class Basic {

    private static Activity Activity;
    private static AppApplication AppContext;


    public static void setActivity(Activity mActivity) {
        Basic.Activity = mActivity;
    }


    public static Activity getActivity() {
        return Activity;
    }

    protected static AppApplication getAppContext() {
        return AppContext;
    }

    public static void
    setAppContext(AppApplication appContext) {
        AppContext = appContext;
    }

    public static Resources getResources() {
        return getAppContext().getResources();
    }



}
