package com.transcendence.core.base.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.transcendence.core.base.action.ToastAction;

import org.greenrobot.eventbus.EventBus;

/**
 * @Author Joephone on 2022/6/13 0013 下午 2:39
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class WanBaseActivity extends TitleBarActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        // Activity销毁时，提示系统回收
        // System.gc();
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 是否注册事件分发，默认不绑定
     */
    protected boolean isRegisterEventBus() {
        return false;
    }


}
