package com.transcendence.core.base.mvp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.transcendence.core.base.mvp.presenter.MvpPresenter;
import com.transcendence.core.base.mvp.view.MvpView;

/**
 * @Author Joephone on 2022/8/1 0001 下午 5:34
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public abstract class MvpFragment<T extends MvpPresenter> extends Fragment implements MvpView{
//    protected T presenter;
//
//    /**
//     * 初始化presenter
//     *
//     * @return P
//     */
//    @Nullable
//    protected abstract T initPresenter();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachPresenter();
//        initialize();
    }

    private void attachPresenter() {
//        if (presenter == null) {
//            presenter = initPresenter();
//        }
//        if (presenter != null) {
//            presenter.attach(this);
//        }
    }

    @Override
    public void onDestroyView() {
//        if (presenter != null) {
//            presenter.detach();
//        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    public Fragment getFragment() {
        return this;
    }

}
