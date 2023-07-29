package com.transcendence.core.ui.popupwindow.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.transcendence.core.R;
import com.transcendence.core.listener.OnMyItemClickListener;
import com.transcendence.core.publicModule.smartrefreshlayout.widget.CommonBaseViewHolder;

import java.util.List;

/**
 * @author joephone
 * @date 2023/4/4
 * @desc
 */
public class PopupBottomAdapter extends BaseQuickAdapter<String, CommonBaseViewHolder> {

    OnMyItemClickListener listener;

    public void setListener(OnMyItemClickListener listener) {
        this.listener = listener;
    }

    public PopupBottomAdapter(@Nullable List<String> data,OnMyItemClickListener listener) {
        super(R.layout.popup_bottom_menu_item, data);
        this.listener = listener;
    }

    @Override
    protected void convert(@NonNull CommonBaseViewHolder helper, String item) {
        helper.setText(R.id.btn_item,item);
        helper.itemView.setOnClickListener(v -> {
            listener.onItemClick(helper.getLayoutPosition());
        });
    }


}
