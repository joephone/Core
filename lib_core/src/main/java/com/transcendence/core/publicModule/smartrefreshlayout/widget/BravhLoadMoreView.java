package com.transcendence.core.publicModule.smartrefreshlayout.widget;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.transcendence.core.R;


public final class BravhLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.layout_brvah_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}