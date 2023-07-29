package com.transcendence.core.base.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.transcendence.core.publicModule.smartrefreshlayout.widget.BravhLoadMoreView;
import com.transcendence.core.publicModule.smartrefreshlayout.widget.CommonBaseViewHolder;

import java.util.List;

/**
 * @author joephone
 * @date 2023/4/11
 * @desc
 */
public abstract class BaseAdapter<T> extends BaseQuickAdapter<T, CommonBaseViewHolder> {

    public BaseAdapter(int layoutResId) {
        super(layoutResId);
        setLoadMoreView(new BravhLoadMoreView());
        //0 关闭动画 1.渐显 2.缩放 3.从下到上 4.从左到右 5.从右到左
//        this.setAnimationWithDefault(BaseQuickAdapter.AnimationType.values()[1]);
    }

    public BaseAdapter(int layoutResId, List<T> data) {
        super(layoutResId,data);
        setLoadMoreView(new BravhLoadMoreView());
        //0 关闭动画 1.渐显 2.缩放 3.从下到上 4.从左到右 5.从右到左
//        this.setAnimationWithDefault(BaseQuickAdapter.AnimationType.values()[1]);
    }
}
