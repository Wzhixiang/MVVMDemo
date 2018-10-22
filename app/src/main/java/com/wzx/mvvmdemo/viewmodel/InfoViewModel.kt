package com.wzx.mvvmdemo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/19
 * 更新时间：
 * 更新内容：
 */
class InfoViewModel(application: Application) : AndroidViewModel(application) {

    var info: ObservableField<String> = ObservableField()

}