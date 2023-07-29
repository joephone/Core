package com.transcendence.core.utils.files;

import com.transcendence.core.base.app.CoreApp;

import java.io.File;


/**
 * 缓存辅助类
 *
 * @author Cuizhen
 * @date 18/4/23
 */
public class CacheUtils {

    /**
     * 获取系统默认缓存文件夹
     * 优先返回SD卡中的缓存文件夹
     */
    public static String getCacheDir() {
        File cacheFile = null;
        if (FileUtils.isSDCardAlive()) {
            cacheFile = CoreApp.getAppContext().getExternalCacheDir();
        }
        if (cacheFile == null) {
            cacheFile = CoreApp.getAppContext().getCacheDir();
        }
        return cacheFile.getAbsolutePath();
    }

    public static String getFilesDir() {
        File cacheFile = CoreApp.getAppContext().getFilesDir();
        return cacheFile.getAbsolutePath();
    }

    /**
     * 获取系统默认缓存文件夹内的缓存大小
     */
    public static String getTotalCacheSize() {
        long cacheSize = FileUtils.getSize(CoreApp.getAppContext().getCacheDir());
        if (FileUtils.isSDCardAlive()) {
            cacheSize += FileUtils.getSize(CoreApp.getAppContext().getExternalCacheDir());
        }
        return FileUtils.formatSize(cacheSize);
    }

    /**
     * 清除系统默认缓存文件夹内的缓存
     */
    public static void clearAllCache() {
        FileUtils.delete(CoreApp.getAppContext().getCacheDir());
        if (FileUtils.isSDCardAlive()) {
            FileUtils.delete(CoreApp.getAppContext().getExternalCacheDir());
        }
    }

}