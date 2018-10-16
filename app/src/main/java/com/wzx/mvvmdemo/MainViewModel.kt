package com.wzx.mvvmdemo

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.ObservableField
import android.support.annotation.NonNull
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import java.util.*

/**
 * 描述：ViewModel
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/16
 * 更新时间：
 * 更新内容：
 */
class MainViewModel(applicition: Application) : AndroidViewModel(applicition) {

    //ObservableField实现双向绑定
    var name: ObservableField<String> = ObservableField("")

    var mUsers: MutableLiveData<User>? = null

    var layoutManager = LinearLayoutManager(applicition)

    var adapter = UserAdapter()

    /**
     * 订阅ViewModel，数据才能正常显示
     *
     * @param owner 宿主
     * @see LifecycleOwner 据有Android生命周期的类
     */
    fun subscribe(@NonNull owner: LifecycleOwner) {
        if (mUsers == null) {
            log("init mUsers")
            mUsers = MutableLiveData()

            //观察mUsers变化
            mUsers?.observe(owner, Observer<User> {
                //监听到变化后，更新列表
                if (it != null) {
                    name.set("")
                    adapter.addData(it)
                }
            })
        }
    }

    fun addUser(view: View) {
        showSnackBar(view, "add ${name.get()} to list")
        val user = User()
        user.name = name.get()
        user.createTime = Date()
        //通知变化
        mUsers?.value = user
    }

    private fun showSnackBar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun log(msg: String) {
        Log.i(this.javaClass.simpleName, msg)
    }
}