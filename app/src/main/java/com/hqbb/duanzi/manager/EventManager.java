package com.hqbb.duanzi.manager;

import com.hqbb.duanzi.bean.support.RefreshCollectionIconEvent;
import com.hqbb.duanzi.bean.support.RefreshCollectionListEvent;
import com.hqbb.duanzi.bean.support.SubEvent;

import de.greenrobot.event.EventBus;

/**
 * @author yuyh.
 * @date 17/1/30.
 */

public class EventManager {

    public static void refreshCollectionList() {
        EventBus.getDefault().post(new RefreshCollectionListEvent());
    }

    public static void refreshCollectionIcon() {
        EventBus.getDefault().post(new RefreshCollectionIconEvent());
    }

    public static void refreshSubCategory(String minor, String type) {
        EventBus.getDefault().post(new SubEvent(minor, type));
    }

}
