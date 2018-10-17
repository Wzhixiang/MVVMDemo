package com.wzx.javaapp;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.wzx.javaapp.BR;

import java.util.Date;

/**
 * 描述：
 * <p>
 * 创建人： Administrator
 * 创建时间： 2018/10/17
 * 更新时间：
 * 更新内容：
 */

public class User extends BaseObservable {

    private String name;
    private Date createTime;
    private int colorId;

    @Bindable
    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        notifyPropertyChanged(BR.createTime);
    }

    @Bindable
    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
        notifyPropertyChanged(BR.colorId);
    }
}
