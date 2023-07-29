package com.transcendence.core.base.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.core.R;

/**
 * @Author Joephone on 2022/6/23 0023 下午 5:07
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class TitleBarActivity extends BaseActivity {

    protected ImageView ivRight;
    protected ImageView ivRight2;
    protected TextView mTvTitle;
    protected boolean isBackVisible = true;

    private void clickBack(View view) {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in_activity, R.anim.left_out_activity);
    }

    protected void setTitle(String title) {
        mTvTitle = findViewById(R.id.tv_title);
        mTvTitle.setText(title);
        setBackVisibility();
    }

    protected void setBackVisibility() {
        FrameLayout fl_back = findViewById(R.id.fl_back);
        ImageView iv_left = findViewById(R.id.iv_left);
        TextView tv_right = findViewById(R.id.tv_right);
        ivRight = findViewById(R.id.iv_right);
        ivRight2 = findViewById(R.id.iv_right2);
        if (isBackVisible) {
            fl_back.setVisibility(View.VISIBLE);
            iv_left.setVisibility(View.VISIBLE);
            fl_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickBack(view);
                }
            });
//            tv_right.setVisibility(View.VISIBLE);
        } else {
            fl_back.setVisibility(View.INVISIBLE);
        }
    }


}
