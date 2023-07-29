package com.transcendence.core.publicModule.smartrefreshlayout.utils;

import android.view.View;

import com.kennyc.view.MultiStateView;
import com.transcendence.core.publicModule.smartrefreshlayout.listener.OnClickListener2;
import com.transcendence.core.publicModule.smartrefreshlayout.listener.SimpleListener2;

/**
 * @author CuiZhen
 * @date 2019/5/25
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class MultiStateUtils {

    public static void toLoading(MultiStateView view){
        view.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    public static void toEmpty(MultiStateView view){
        if (view.getViewState() == MultiStateView.VIEW_STATE_CONTENT) {
            return;
        }
        view.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    public static void toError(MultiStateView view){
        if (view.getViewState() == MultiStateView.VIEW_STATE_CONTENT) {
            return;
        }
        view.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }

    public static void toContent(MultiStateView view){
        view.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    public static void setEmptyAndErrorClick(MultiStateView view, SimpleListener2 listener){
        setEmptyClick(view, listener);
        setErrorClick(view, listener);
    }

    public static void setEmptyClick(MultiStateView view, SimpleListener2 listener){
        View empty = view.getView(MultiStateView.VIEW_STATE_EMPTY);
        if (empty != null) {
            empty.setOnClickListener(new OnClickListener2() {
                @Override
                public void onClick2(View v) {
                    listener.onResult();
                }
            });
        }
    }

    public static void setErrorClick(MultiStateView view, SimpleListener2 listener){
        View error = view.getView(MultiStateView.VIEW_STATE_ERROR);
        if (error != null) {
            error.setOnClickListener(new OnClickListener2() {
                @Override
                public void onClick2(View v) {
                    listener.onResult();
                }
            });
        }
    }
}
