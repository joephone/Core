package com.transcendence.core.base.mvvm.fragment;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.core.R;


/**
 * @Author Joephone on 2022/8/2 0002 下午 5:18
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class TitleBarFragment extends WanBaseFragment {

    protected boolean isBackVisible = true;
    protected ImageView mIvRight;

    protected void setTitle(String title) {
        ((TextView) findViewById(R.id.tv_title)).setText(title);
    }

    protected void setBackVisibility() {
        FrameLayout fl_back = (FrameLayout) findViewById(R.id.fl_back);
        if (isBackVisible) {
            fl_back.setVisibility(View.VISIBLE);
            fl_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickBack(view);
                }
            });
        } else {
            fl_back.setVisibility(View.INVISIBLE);
        }

    }

    private void clickBack(View view) {
        finish();
    }

    protected void setRightImage(int resId) {
        mIvRight = ((ImageView) findViewById(R.id.iv_right));
        mIvRight.setVisibility(View.VISIBLE);
        mIvRight.setImageResource(resId);
    }

}
