package com.hqbb.duanzi.util;

import android.text.TextUtils;

import com.hqbb.duanzi.base.Constant;
import com.hqbb.duanzi.bean.BookLists;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ylh on 2018/2/23 0023.
 */

public class CacheManage {

    private String getCollectionKey() {
        return "my_book_lists";
    }

    /**
     * 获取缓存大小
     * @return
     */
    public synchronized String getCacheSize() {
        long cacheSize = 0l;
        try {
            String cacheDir = Constant.BASE_PATH;
            cacheSize += FileUtils.getFolderSize(cacheDir);

            if (FileUtils.isSdCardAvailable()) {
                String extCacheDir = AppUtils.getAppContext().getExternalCacheDir().getPath();
                cacheSize += FileUtils.getFolderSize(extCacheDir);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return FileUtils.formatFileSizeToString(cacheSize);

    }

    /**
     * 清除缓存
     *
     * @param clearReadPos 是否删除阅读记录
     */
    public synchronized void clearCache(boolean clearReadPos, boolean clearCollect) {
        try {
            // 删除内存缓存
            String cacheDir = AppUtils.getAppContext().getCacheDir().getPath();
            FileUtils.deleteFileOrDirectory(new File(cacheDir));
            if (FileUtils.isSdCardAvailable()) {
                // 删除SD书籍缓存
                FileUtils.deleteFileOrDirectory(new File(Constant.PATH_DATA));
            }
            // 删除阅读记录（SharePreference）
            if (clearReadPos) {
            }
            // 清空书架
            if (clearCollect) {
            }
            // 清除其他缓存
            ACache.get(AppUtils.getAppContext()).clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
