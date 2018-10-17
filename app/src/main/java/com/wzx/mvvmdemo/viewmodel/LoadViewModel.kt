package com.wzx.mvvmdemo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleOwner
import android.databinding.ObservableField
import android.support.annotation.NonNull

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/17
 * 更新时间：
 * 更新内容：
 */
class LoadViewModel(application: Application):AndroidViewModel(application) {

    var state: ObservableField<String> = ObservableField("")

    fun subscribe(@NonNull owner: LifecycleOwner) {

    }
}