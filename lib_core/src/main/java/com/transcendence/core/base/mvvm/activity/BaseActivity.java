package com.transcendence.core.base.mvvm.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.transcendence.core.R;
import com.transcendence.core.base.action.ActivityAction;
import com.transcendence.core.base.action.KeyboardAction;
import com.transcendence.core.utils.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author Joephone on 2022/6/13 0013 下午 2:40
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */

public abstract class BaseActivity extends AppCompatActivity implements ActivityAction, KeyboardAction {

    protected static BaseActivity mActivity;
    private Unbinder mUnbinder = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        StatusBarUtils.with(mActivity).init();
        init();
    }

    protected void init() {
        initLayout();
        loadData();
    }

    /**
     * 初始化布局
     */
    protected void initLayout() {
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            mUnbinder = ButterKnife.bind(this);
        }
    }

    /**
     * 获取布局 ID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void loadData();

    /**
     *   ActivityAction
     */
    @Override
    public Context getContext() {
        return this;
    }


    protected static void start(Class<?> target) {
        Intent intent = new Intent(mActivity, target);
        mActivity.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.wan_zoom_small_in, R.anim.wan_zoom_small_out);
    }

    /**
     * A Tip
     * @param msg
     */
    protected void showMsg(CharSequence msg){
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 如果当前的 Activity（singleTop 启动模式） 被复用时会回调
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 设置为当前的 Intent，避免 Activity 被杀死后重启 Intent 还是最原先的那个
        setIntent(intent);
    }
}
