package com.transcendence.core.publicModule.picture.act;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.transcendence.core.R;
import com.transcendence.core.base.activity.BaseMvvmActivity;
import com.transcendence.core.base.app.CoreApp;
import com.transcendence.core.base.mvvm.BaseViewModel;
import com.transcendence.core.databinding.ActivityPictureViewBinding;
import com.transcendence.core.publicModule.picture.adapter.PictureAdapter;
import com.transcendence.core.publicModule.picture.bean.PictureItemBean;
import com.transcendence.core.publicModule.picture.bitmap.BitmapUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author joephone
 * @date 2023/4/3
 * @desc
 */
public class PictureViewActivity extends BaseMvvmActivity<ActivityPictureViewBinding, BaseViewModel> {

    List<PictureItemBean> data = new ArrayList<>();
    private int storeRequestCode = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture_view;
    }

    @Override
    protected void initView() {
        //延迟动画
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
        }

        data  = (List<PictureItemBean>) getIntent().getSerializableExtra("img");
        int index = getIntent().getIntExtra("index",0);
        dataBinding.vp.setAdapter(new PictureAdapter(PictureViewActivity.this,data));
        dataBinding.vp.setCurrentItem(index,false);

        dataBinding.tvSave.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            } else {
                //保存图片到相册
                saveImage();
            }
        });

        dataBinding.vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setIndex(position);
            }
        });

        setIndex(index);
    }

    private void saveImage() {
        Glide.with(CoreApp.getInstance())
                .asBitmap()
                .load(data.get(dataBinding.vp.getCurrentItem()).getImg())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        BitmapUtils.saveGallery(resource,"wan_wallpaper_" + System.currentTimeMillis());
                    }
                });
    }

    private void setIndex(int index){
        dataBinding.tvIndex.setText(index+"/"+data.size());
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == storeRequestCode) {
            saveImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    saveImage();
                } else {
                    //点击了不再提示,拒绝权限
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                        //跳转到设置界面
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, storeRequestCode);
                    } else {
                        showMsg("获取权限失败");
                    }
                }
                break;
            default:
        }
    }
}
