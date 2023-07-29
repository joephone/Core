package com.transcendence.core.ui.dialog;

import android.app.Dialog;
import android.content.Context;

import com.transcendence.core.R;

/**
 * loading弹窗
 *
 * @author Cuizhen
 * @date 2018/6/21-上午10:00
 */
public class LoadingDialog {

    private static final long ANIM_DURATION = 200;
    private final Context context;
    private Dialog mDialog;
    private int count = 0;

    private LoadingDialog(Context context) {
        this.context = context;
    }

    public static LoadingDialog with(Context context) {
        return new LoadingDialog(context);
    }

    public void show() {
        if (count <= 0) {
            count = 0;
            mDialog = new Dialog(context, R.style.DialogTheme);
            mDialog.setContentView(R.layout.dialog_loading_basic);
            mDialog.show();
        }
        count++;
    }

    public void dismiss() {
        count--;
        if (count <= 0) {
            clear();
        }
    }

    public void clear() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
        count = 0;
    }
}
