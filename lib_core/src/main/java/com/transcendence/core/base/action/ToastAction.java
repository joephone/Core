package com.transcendence.core.base.action;

import android.widget.Toast;

import com.transcendence.core.base.app.CoreApp;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/12/08
 *    desc   : 吐司意图
 */
public interface ToastAction {

    default void toast(CharSequence text) {
        Toast.makeText(CoreApp.getAppContext(),text,Toast.LENGTH_SHORT).show();
    }


}