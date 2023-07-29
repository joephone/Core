package com.transcendence.core.publicModule.picture.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 壁纸列表返回数据
 * @author llw
 */
public class PictureItemBean implements Serializable {

    private String id;
    private String img;
    private int index;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

