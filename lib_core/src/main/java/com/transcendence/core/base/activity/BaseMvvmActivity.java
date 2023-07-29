package com.transcendence.core.base.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.transcendence.core.base.mvvm.BaseViewModel;
import com.transcendence.core.utils.StatusBarUtils;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author joephone
 * @date 2023/3/25
 * @desc
 */
public abstract class BaseMvvmActivity<V extends ViewDataBinding,VM extends BaseViewModel> extends AppCompatActivity {

    protected V dataBinding;
    protected BaseMvvmActivity mActivity;
    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        StatusBarUtils.with(mActivity).init();
        if(getLayoutId()!=0){
            dataBinding = DataBindingUtil.setContentView(this,getLayoutId());
        }

        init();

        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    protected void init() {
        viewModel = creatViewModel();
        initView();
        loadData();
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
    }

    protected VM creatViewModel(){
        if (viewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            viewModel = (VM) new ViewModelProvider(this,
                    ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                    .get(modelClass);
        }
        return viewModel;
    }

    /**
     * 获取布局 ID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void loadData();


    @Override
    protected void onDestroy() {
        // Activity销毁时，提示系统回收
        // System.gc();
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 是否注册事件分发，默认不绑定
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    protected void showMsg(CharSequence msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
