package com.transcendence.core.base.mvp.presenter;

import android.content.Context;

import com.transcendence.core.base.mvp.view.MvpView;

/**
 * @Author Joephone on 2022/8/1 0001 下午 2:59
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class MvpPresenter<V extends MvpView> {

    protected Context context;
    private V baseView;

    public void attach(V baseView) {
        this.baseView = baseView;
        context = baseView.getContext();
    }

    public void detach() {
        baseView = null;
        context = null;
    }

    public V getBaseView() {
        return baseView;
    }

    public boolean isAttach() {
        return baseView != null;
    }

    public Context getContext() {
        return context;
    }

    public void showLoadingDialog() {
        if (baseView != null) {
            baseView.showLoadingDialog();
        }
    }

    public void dismissLoadingDialog() {
        if (baseView != null) {
            baseView.dismissLoadingDialog();
        }
    }


}
