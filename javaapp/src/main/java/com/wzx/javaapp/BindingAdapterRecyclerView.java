package com.wzx.javaapp;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 描述：
 * <p>
 * 创建人： Administrator
 * 创建时间： 2018/10/17
 * 更新时间：
 * 更新内容：
 */

public class BindingAdapterRecyclerView {

    @BindingAdapter({"adapter", "laoutManager"})
    public static void initRecycler(@NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.Adapter<RecyclerView.ViewHolder> adapter,
                                    @Nullable RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager == null ? new LinearLayoutManager(recyclerView.getContext()) : layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
