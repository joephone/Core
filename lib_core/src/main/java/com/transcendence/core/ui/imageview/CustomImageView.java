package com.transcendence.core.ui.imageview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.transcendence.core.R;
import com.transcendence.core.base.app.CoreApp;
import com.transcendence.core.utils.log.LogUtils;

/**
 * 自定义View
 *
 * @author llw
 */
public class CustomImageView extends ShapeableImageView {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .placeholder(R.drawable.ic_empty)//图片加载出来前，显示的图片
            .fallback(R.drawable.ic_empty) //url为空的时候,显示的图片
            .error(R.drawable.ic_error);//图片加载失败后，显示的图片

    private static final RequestOptions OPTIONS_LOCAL = new RequestOptions()
            .placeholder(R.drawable.ic_empty)//图片加载出来前，显示的图片
            .fallback(R.drawable.ic_empty) //url为空的时候,显示的图片
            .error(R.drawable.ic_error)//图片加载失败后，显示的图片
            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
            .skipMemoryCache(true);

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 必应壁纸  因为拿到的url不完整，因此需要做一次地址拼接
     *
     * @param imageView 图片视图
     * @param url       网络url
     */
    @BindingAdapter(value = {"biyingUrl"}, requireAll = false)
    public static void setBiyingUrl(ImageView imageView, String url) {
        String assembleUrl = "http://cn.bing.com" + url;
        LogUtils.d(assembleUrl);
        Glide.with(CoreApp.getInstance()).load(assembleUrl).into(imageView);
    }

    /**
     * 普通网络地址图片
     *
     * @param imageView 图片视图
     * @param url       网络url
     */
    @BindingAdapter(value = {"networkUrl"}, requireAll = false)
    public static void setNetworkUrl(ImageView imageView, String url) {
        Glide.with(CoreApp.getInstance()).load(url).apply(OPTIONS).into(imageView);
    }

    /**
     * 本地地址图片
     *
     * @param imageView 图片视图
     * @param url       本地url
     */
    @BindingAdapter(value = {"localUrl"}, requireAll = false)
    public static void setLocalUrl(ImageView imageView, String url) {
        Glide.with(CoreApp.getInstance()).load(url).apply(OPTIONS_LOCAL).into(imageView);
    }
}
