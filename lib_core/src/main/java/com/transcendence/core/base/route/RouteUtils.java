package com.transcendence.core.base.route;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

import com.transcendence.core.utils.log.LogUtils;

/**
 * @author joephone
 * @date 2022/11/29
 * @desc
 */
public class RouteUtils {

    public static void navigationActivity(String path){
        ARouter.getInstance().build(path).navigation();
    }

    public static Fragment navigationFragment(String path){
        return (Fragment) ARouter.getInstance().build(path).navigation();
    }

    public static void navigationWeb(String webUrl) {
        ARouter.getInstance().build(RoutePath.Web.PAGER_WEB).withString("webUrl", webUrl).navigation();
    }


}
