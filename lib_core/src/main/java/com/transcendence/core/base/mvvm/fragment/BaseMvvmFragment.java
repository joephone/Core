package com.transcendence.core.base.mvvm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.transcendence.core.base.mvvm.BaseViewModel;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author joephone
 * @date 2023/3/25
 * @desc
 */
public abstract class BaseMvvmFragment<V extends ViewDataBinding,VM extends BaseViewModel> extends Fragment {

    protected V dataBinding;
    protected VM viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewDataBinding();
    }

    private void initViewDataBinding(){
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
                    ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                    .get(modelClass);
        }

        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);

        initView();
        loadData();
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void loadData();

    /**
     * 是否注册事件分发，默认不绑定
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    protected void showMsg(CharSequence msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
