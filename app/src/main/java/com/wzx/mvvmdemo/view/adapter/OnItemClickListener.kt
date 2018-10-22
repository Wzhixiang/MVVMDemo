package com.wzx.mvvmdemo.view.adapter

import android.view.View

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/19
 * 更新时间：
 * 更新内容：
 */
interface OnItemClickListener<T> {
    fun onItemClick(view: View?, position: Int, t: T?)
    fun onItemLongClick(view: View?, position: Int, t: T?)
}