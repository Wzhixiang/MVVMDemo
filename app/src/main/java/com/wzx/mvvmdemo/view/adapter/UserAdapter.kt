package com.wzx.mvvmdemo.view.adapter

import android.databinding.DataBindingUtil
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.wzx.mvvmdemo.R
import com.wzx.mvvmdemo.model.bean.User
import com.wzx.mvvmdemo.databinding.ItemUserBinding

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/16
 * 更新时间：
 * 更新内容：
 */
class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var data = arrayListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            UserViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_user, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = data[position]

        if (position % 2 == 0)
            user.colorId = ContextCompat.getColor(holder.itemView.context, R.color.colorAccent)
        else
            user.colorId = ContextCompat.getColor(holder.itemView.context, R.color.colorPrimary)

        holder.dataBinding.user = user
        //执行绑定
        holder.dataBinding.executePendingBindings()
    }

    fun addData(users: ArrayList<User>) {
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }

    class UserViewHolder(var dataBinding: ItemUserBinding) : RecyclerView.ViewHolder(dataBinding.root)
}