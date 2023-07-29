package com.transcendence.core.publicModule.picture.adapter;

import static com.transcendence.core.utils.ResUtils.getString;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.transcendence.core.R;
import com.transcendence.core.base.adapter.BaseAdapter;
import com.transcendence.core.base.app.CoreApp;
import com.transcendence.core.listener.OnMyItemClickListener;
import com.transcendence.core.publicModule.picture.bean.PictureItemBean;
import com.transcendence.core.publicModule.picture.bitmap.BitmapUtils;
import com.transcendence.core.publicModule.smartrefreshlayout.widget.CommonBaseViewHolder;
import com.transcendence.core.ui.popupwindow.SelectPicPopupWindow;
import com.transcendence.core.utils.glide.GlideWrapper;
import com.transcendence.core.utils.log.LogUtils;

import java.util.List;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * @author joephone
 * @date 2023/4/2
 * @desc
 */
public class PictureAdapter extends BaseAdapter<PictureItemBean> implements PromptButtonListener {

    private Activity mActivity;
    private List<PictureItemBean> mData;
    SelectPicPopupWindow mPopupWindow;
    private PromptDialog promptDialog;

    public PictureAdapter(Activity activity,List<PictureItemBean> data){
        super(R.layout.picture_item,data);
        this.mActivity = activity;
        this.mData = data;
        initDialog();
    }
    private int mPosition;

    @Override
    protected void convert(@NonNull CommonBaseViewHolder helper, PictureItemBean item) {
        ImageView iv = helper.itemView.findViewById(R.id.image);
        GlideWrapper.getInstance().loadImageFromUrl(item.getImg(),iv);

        iv.setOnClickListener(v -> {
            LogUtils.d("click");

            PromptButton cancelBtn= new PromptButton(getString(R.string.cancel), null);
            cancelBtn.setTextColor(Color.BLACK);
            PromptButton localBtn = new PromptButton("保存至相册", null);
            localBtn.setListener(this::onClick);
            promptDialog.showAlertSheet(true,"", true, cancelBtn,localBtn);
//            mPosition = helper.getLayoutPosition();
//            mPopupWindow =new SelectPicPopupWindow(mActivity,listener);
        });
    }

    private void initDialog() {
        promptDialog = new PromptDialog(mActivity);
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);

    }

    OnMyItemClickListener listener = new OnMyItemClickListener() {
        @Override
        public void onItemClick(int position) {
            switch (position){
                case 0:
                    mPopupWindow.dismiss();
                    Glide.with(CoreApp.getInstance())
                    .asBitmap()
                    .load(mData.get(mPosition).getImg())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            BitmapUtils.saveGallery(resource,"wan_wallpaper_" + System.currentTimeMillis());
                        }
                    });
                    break;
            }

        }
    };

    @Override
    public void onClick(PromptButton button) {
        Glide.with(CoreApp.getInstance())
                .asBitmap()
                .load(mData.get(mPosition).getImg())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        BitmapUtils.saveGallery(resource,"wan_wallpaper_" + System.currentTimeMillis());
                    }
                });
    }
}
