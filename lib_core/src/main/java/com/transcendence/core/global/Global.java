package com.transcendence.core.global;

import com.transcendence.core.base.app.CoreApp;

/**
 * @Author Joephone on 2022/6/13 0013 下午 5:33
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class Global {

    public static final String TAG = "wan";

    public final static boolean DEBUG = true;

    public final static int ANIM_DURATION_TIME = 500;

    public static final int avatarImgCount = 1;               //允许选择图片最大数

    public static final int PAGE_START = 0;

    public static final String BUGLY_ID = "79ac952bd8";
    public static final String BUGLY_KEY = "fdc5280a-0806-4763-9469-ed650273132f";

    //文件夹名称
    public static final String FILE_TYPE = "FullLog";
    public static final int DEFAULT_LOG_MAX_LIENS = 20;
    //存在日期
    public static final int FULL_LOG_MAX_KEEP_DAYS = 3;

    public static final String APPLICATION_ID = "com.transcendence.wan";
    public static final String FILE_PROVIDIER_AUTHORITY = APPLICATION_ID + ".file_provider";


    public class SP_KEY {
        public static final String SP_A_P = "sp_a_p";
        public static final String SP_ACCOUNT = "sp_account";
        public static final String SP_HAD_OPEN_FINGERPRINT_LOGIN = "sp_had_open_fingerprint_login";
        public static final String SP_LOCAL_FINGERPRINT_INFO = "sp_local_fingerprint_info";
        public static final String LAUNCH_TIME = "launch_time";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
    }



    public final class PUBLIC_INTENT_KEY {
        public static final String ID  = "id";
        public static final String LOCATION  = "location";
        public static final String SYNC  = "sync";
        public static final String WX_PAY_RESULT  = "wxPayResult";
        public static final String OTHER  = "other";
    }

    public class REQUEST_CODE {
        public static final int REQUEST_CODE_RECORD_VIDEO = 10020;
    }

    public static final class DIRECTORY {
        public static final String FULL_LOG =  CoreApp.getAppContext().getExternalFilesDir(FILE_TYPE).getAbsolutePath(); //File directory = mAppContext.getExternalFilesDir("FullLog");
        public static final String CRASH =  CoreApp.getAppContext().getExternalCacheDir().getAbsolutePath();
    }

}
