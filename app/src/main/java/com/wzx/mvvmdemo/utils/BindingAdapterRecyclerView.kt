package com.wzx.mvvmdemo.utils

import android.databinding.BindingAdapter
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/16
 * 更新时间：
 * 更新内容：
 */
class BindingAdapterRecyclerView {

    @BindingAdapter("adapter", "laoutManager")
    open fun initRecycler(@NonNull recyclerView: RecyclerView,
                     @NonNull adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
                     @Nullable layoutManager: RecyclerView.LayoutManager?) {
        recyclerView.layoutManager = layoutManager ?: LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }
}