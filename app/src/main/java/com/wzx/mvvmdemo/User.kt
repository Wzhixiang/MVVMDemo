package com.wzx.mvvmdemo

import android.databinding.BaseObservable
import android.databinding.Bindable
import java.util.*

/**
 * 描述：实现双向绑定，需要将类继承BaseObservable，然后属性的get方法需要使用@Bindable注解，作用是允许view绑定该属性，还需要在set方法调用notifyPropertyChanged
 * @see BaseObservable
 * @see Bindable
 * 创建人： Administrator
 * 创建时间： 2018/10/16
 * 更新时间：
 * 更新内容：
 */
class User : BaseObservable() {

    var name: String? = null
        @Bindable
        get() = field ?: ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    var createTime: Date? = null
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.createTime)
        }

    var colorId: Int? = null
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.colorId)
        }
}