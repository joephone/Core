package com.transcendence.core.base.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.tencent.bugly.crashreport.CrashReport;
import com.transcendence.core.BuildConfig;
import com.transcendence.core.base.manager.ActivityManager;
import com.transcendence.core.global.Global;
import com.transcendence.core.publicModule.db.DatabaseHelper;

/**
 * @author Joephone on 2019/5/6 11:42
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc 全局
 */
public class CoreApp extends Application {

    //以下属性应用于整个应用程序，合理利用资源，减少资源浪费
    private static Context applicationContext;   //上下文
    private static CoreApp instance;
    private static Handler mHandler = new Handler();//主线程Handler
    private static DatabaseHelper sDBhepler;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //对全局属性赋值
        instance = this;
        applicationContext = getApplicationContext();
        // Activity 栈管理初始化
        ActivityManager.getInstance().init(this);
        //初始化本地数据库
        sDBhepler = new DatabaseHelper(this);
        // Bugly 异常捕捉
        CrashReport.initCrashReport(this, Global.BUGLY_ID, BuildConfig.DEBUG);
    }


    public static CoreApp getInstance() {
        if (instance == null) {
            instance = new CoreApp();
        }
        return instance;
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 重启当前应用
     */
    public static void reStart() {
        Intent intent = applicationContext.getPackageManager().getLaunchIntentForPackage(applicationContext.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        applicationContext.startActivity(intent);
    }

    public static Context getAppContext() {
        return applicationContext;
    }

    public static void setContext(Context mContext) {
        CoreApp.applicationContext = mContext;
    }

    public static Handler getMainHandler() {
        return mHandler;
    }

    public static void setMainHandler(Handler mHandler) {
        CoreApp.mHandler = mHandler;
    }

    public static DatabaseHelper getDBhepler() {
        return sDBhepler;
    }
}
