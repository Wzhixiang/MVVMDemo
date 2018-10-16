package com.wzx.mvvmdemo

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
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

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            UserViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent?.context), R.layout.item_user, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: UserViewHolder?, position: Int) {
        holder?.dataBinding?.user = data[position]
        //执行绑定
        holder?.dataBinding?.executePendingBindings()
    }

    fun addData(vararg users: User) {
        data.addAll(users)
        notifyDataSetChanged()
    }

    class UserViewHolder(var dataBinding: ItemUserBinding) : RecyclerView.ViewHolder(dataBinding.root)
}