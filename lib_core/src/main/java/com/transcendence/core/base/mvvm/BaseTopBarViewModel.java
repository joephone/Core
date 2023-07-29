package com.transcendence.core.base.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

/**
 * @author joephone
 * @date 2022/11/30
 * @desc
 */
public class BaseTopBarViewModel<M extends BaseModel> extends BaseViewModel<M>{

    /**
     * 兼容databinding，去泛型化
     */
    public BaseTopBarViewModel toolbarViewModel;
    public ObservableField<String> titleText = new ObservableField<>();
    public ObservableField<String> rightImage = new ObservableField<>();
//    public ObservableField<Boolean> leftImage = new ObservableField<>();

    public BaseTopBarViewModel(@NonNull Application application) {
        super(application);
        toolbarViewModel = this;
    }

    public void setTitle(String title) {
        titleText.set(title);
    }

    public void setRightImage(String id){
        rightImage.set(id);
    }

//    public void setBackVisible(Boolean visible){
//        leftImage.set(visible);
//    }
}
