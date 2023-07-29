package com.transcendence.core.base.activity;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zyq.easypermission.EasyPermission;
import com.zyq.easypermission.EasyPermissionHelper;
import com.zyq.easypermission.EasyPermissionLog;
import com.zyq.easypermission.bean.PermissionAlertInfo;

/**
 * @Author Joephone on 2022/6/29 0029 下午 12:05
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class PermissionWithExplainActivity extends TitleBarActivity {

    protected EasyPermission mEasyPermission;
    protected PermissionAlertInfo mAlertInfo;
    /**
     * 不同的权限应该有不同的回调code
     */
    protected static final int RC_CODE_PERMISSION = 1024;
    protected static final int RC_CODE_PERMISSION_PHONENO = 1025;


    /**
     * 在baseActivity覆盖该方法,或者每个活动需要使用EasyPermission
     * Override this method in baseActivity ，or each Activity who need use EasyPermission
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //使用EasyPermissionHelper注入回调
//        Inject the callback using EasyPermissionHelper
        EasyPermissionHelper.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 需要从系统设置界面返回时判断权限情况的，在baseActivity覆盖该方法,或者每个活动需要使用EasyPermission
     * Override this method in baseActivity ，or each Activity who need use EasyPermission，
     * if you want to get the new state form settings page
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //使用EasyPermissionHelper注入回调
//        Inject the callback using EasyPermissionHelper
        EasyPermissionHelper.getInstance().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        EasyPermissionLog.d(mActivity.getLocalClassName()+": onNewIntent");
        //launchMode="singleTask" onNewIntent中如果需要请求权限，需要重新设置activity
        //注意：这里并不是说必须在onNewIntent这样做，而是非要在onNewIntent执行权限请求的话，才需要设置mContext。
        //正常情况下在onCreate和onResume中不需要设置
        //方式一：
        EasyPermissionHelper.getInstance().updateTopActivity(mActivity);
        try{
            mEasyPermission.requestPermission();
        }catch (Exception e){

        }

//        //方式二:
//        easyPermission.mContext(mContext).requestPermission();
    }
}
