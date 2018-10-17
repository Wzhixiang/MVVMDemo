package com.wzx.mvvmdemo.model.bean

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.databinding.BaseObservable
import android.databinding.Bindable
import com.wzx.mvvmdemo.BR
import java.util.*

/**
 * 描述：实现双向绑定，需要将类继承BaseObservable，然后属性的get方法需要使用@Bindable注解，作用是允许view绑定该属性，还需要在set方法调用notifyPropertyChanged
 *
 * @Entity 注解该data class表示这是一个数据表
 *
 * @see BaseObservable 继承BaseObservable是为了该类可以双向绑定
 * @see Bindable 绑定参数
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/16
 * 更新时间：parameter
 * 更新内容：
 */
@Entity
data class User(@PrimaryKey var id: Int? = null) : BaseObservable() {

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