package com.transcendence.core.ui.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.transcendence.core.R;
import com.transcendence.core.listener.OnMyItemClickListener;
import com.transcendence.core.ui.popupwindow.adapter.PopupBottomAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author joephone
 * @date 2023/4/4
 * @desc
 */
public class SelectPicPopupWindow extends PopupWindow {

    private View mPopView;
    private TextView tv_cancel;
    private RecyclerView mRv;
    private LinearLayout layout;
    private PopupBottomAdapter adapter;
    private List<String> itemList = new ArrayList<>();

    public SelectPicPopupWindow(Activity context, OnMyItemClickListener listener){
        super(context);
        init(context,listener);
    }

    private void init(Activity context, OnMyItemClickListener listener) {
        LayoutInflater inflater = LayoutInflater.from(context);        //绑定布局
        mPopView = inflater.inflate(R.layout.popup_bottom_menu, null);
        this.setContentView(mPopView);// 设置View

        initView(context,listener);

        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);// 设置弹出窗口的宽
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);// 设置弹出窗口的高
        this.setFocusable(true);// 设置弹出窗口可
        this.setAnimationStyle(R.style.PopupBottom);// 设置动画

        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(new ColorDrawable(0xb0000000));  //半透明  0xb0000000   透明 0x00FFFFFF
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mPopView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                int height = mPopView.findViewById(R.id.pop_layout).getTop();
//                int y = (int) event.getY();
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    if (y < height) {
//                        dismiss();
//                    }
//                }
                dismiss();
//                if(listener!=null){
//                    listener.onSignInSuc();
//                }
                return true;
            }
        });

        // 在底部显示
        this.showAtLocation(mPopView, Gravity.BOTTOM, 0, 0);

    }

    private void initView(Activity context, OnMyItemClickListener listener) {
        mRv = mPopView.findViewById(R.id.rv);
        tv_cancel = mPopView.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(v -> {
            this.dismiss();
        });

        LinearLayoutManager manager = new LinearLayoutManager(context);
        mRv.setLayoutManager(manager);

        itemList.add("保存至相册");
        adapter = new PopupBottomAdapter(itemList,listener);
        mRv.setAdapter(adapter);

    }

    public void dismiss(){
        if(this.isShowing()){
            this.dismiss();
        }
    }


}
