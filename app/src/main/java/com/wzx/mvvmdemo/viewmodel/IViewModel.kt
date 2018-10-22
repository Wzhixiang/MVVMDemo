package com.wzx.mvvmdemo.viewmodel

import android.arch.lifecycle.LifecycleOwner
import android.support.annotation.NonNull

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/19
 * 更新时间：
 * 更新内容：
 */
interface IViewModel<T : LifecycleOwner> {

    fun subscribe(@NonNull t: T)

}