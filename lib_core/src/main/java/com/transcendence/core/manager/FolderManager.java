package com.transcendence.core.manager;

import android.os.Environment;

import com.transcendence.core.base.app.CoreApp;
import com.transcendence.core.utils.log.LogUtils;

import java.io.File;

/**
 * @author joephone
 * @date 2023/7/28
 * @desc
 */
public class FolderManager {


    private static final String DOWNLOAD = "download";
    private static final String PIC = "pic";

//    private static File appExternalDirectory = new File(Environment.getExternalStorageDirectory(), APP_FOLDER);

    public static final String DOWNLOAD_DIR =  CoreApp.getAppContext().getExternalFilesDir(DOWNLOAD).getAbsolutePath();
    public static final String PIC_DIR =  CoreApp.getAppContext().getExternalFilesDir(PIC).getAbsolutePath();

    private static File downloadDirectory = new File(DOWNLOAD_DIR);
    private static File innerImagesDirectory = new File( PIC_DIR);

    public static File getDownloadDirectory() {
        LogUtils.d("Environment.getExternalStorageDirectory()--"+Environment.getExternalStorageDirectory());
        LogUtils.d("CoreApp.getAppContext().getExternalFilesDir(DOWNLOAD)--"+CoreApp.getAppContext().getExternalFilesDir(DOWNLOAD).getAbsolutePath());
        return downloadDirectory;
    }

    public static File getInnerImagesDirectory() {
        return innerImagesDirectory;
    }

}
