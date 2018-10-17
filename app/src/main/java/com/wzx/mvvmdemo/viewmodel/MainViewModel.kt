package com.wzx.mvvmdemo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.ObservableField
import android.support.annotation.NonNull
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.wzx.mvvmdemo.BaseObserver
import com.wzx.mvvmdemo.model.LocalData
import com.wzx.mvvmdemo.model.bean.User
import com.wzx.mvvmdemo.view.MainActivity
import com.wzx.mvvmdemo.view.adapter.UserAdapter
import com.wzx.mvvmdemo.view.dialog.LoadDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

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

    private var dataChange: MutableLiveData<Boolean>? = null

    var layoutManager = LinearLayoutManager(applicition)

    var adapter = UserAdapter()

    private val loadDialog: LoadDialog by lazy {
        LoadDialog()
    }

    private val dataBase: LocalData by lazy {
        LocalData(applicition)
    }

    /**
     * 订阅
     * @param activity 观察者
     */
    fun subscribe(@NonNull activity: MainActivity) {
        if (dataChange == null) {
            log("init mUsers")
            dataChange = MutableLiveData()

            //观察mUsers变化
            dataChange?.observe(activity, Observer<Boolean> {
                //监听到变化后，更新用户列表
                it.let {
                    if (it!!) {
                        showLoading(activity.supportFragmentManager)

                        dataBase.getUsers()
                                .delay(1000, TimeUnit.MILLISECONDS)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(object : BaseObserver<List<User>>() {
                                    override fun onSuccess(t: List<User>) {
                                        name.set("")
                                        adapter.addData(ArrayList(t))
                                        dimissLoading()
                                    }

                                    override fun onFail(msg: String?) {
                                        log(msg!!)
                                        dimissLoading()
                                    }
                                })
                    }
                }
            })
        }

        dataChange?.value = true
    }

    /**
     * 添加用户，在Button中触发
     */
    fun addUser(view: View) {
        if (TextUtils.isEmpty(name.get())) {
            showSnackBar(view, "name is null")
            return
        }

        closeSoftBoard(view.context)

        try {
            showLoading((view.context as MainActivity).supportFragmentManager)
        } catch (e: Exception) {
            //context强制activity可能会除错
            e.printStackTrace()
        }

        val user = User()
        user.name = name.get()
        user.createTime = Date()

        dataBase.addUser(user)
                //延迟1秒
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<Boolean>() {
                    override fun onSuccess(t: Boolean) {
                        dataChange?.value = true
                    }

                    override fun onFail(msg: String?) {
                        log(msg!!)
                    }
                })
    }

    /**
     * 显示LoadDialog
     * @param fragmentManager
     */
    private fun showLoading(fragmentManager: FragmentManager) {
        if (loadDialog == null) {
            loadDialog.show(fragmentManager, LoadDialog.TAG)
        } else {
            if (!loadDialog.isVisible) {
                //当LoadDialog没有显示时，才触发显示
                loadDialog.show(fragmentManager, LoadDialog.TAG)
            }
        }
    }

    /**
     * LoadDialog消失
     */
    private fun dimissLoading() {
        loadDialog.let {
            //当LoadDialog显示时，才触发消失
            if (it.isVisible) it.dismiss()
        }
    }

    /**
     * 关闭软键盘
     */
    private fun closeSoftBoard(context: Context) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.let {
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }
    }

    private fun showSnackBar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun log(msg: String) {
        Log.i(this.javaClass.simpleName, msg)
    }
}