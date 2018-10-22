package com.wzx.mvvmdemo.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.support.annotation.NonNull
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.wzx.mvvmdemo.BaseObserver
import com.wzx.mvvmdemo.R
import com.wzx.mvvmdemo.model.LocalData
import com.wzx.mvvmdemo.model.bean.User
import com.wzx.mvvmdemo.view.MainActivity
import com.wzx.mvvmdemo.view.UserDetailActivity
import com.wzx.mvvmdemo.view.UserDetailActivity.Companion.Request_Code
import com.wzx.mvvmdemo.view.adapter.OnItemClickListener
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

class MainViewModel(applicition: Application) : BaseViewModel<MainActivity>(applicition) {

    //ObservableField实现双向绑定
    var name: ObservableField<String> = ObservableField("")

    var dataChange: MutableLiveData<Boolean>? = null

    var adapter = UserAdapter(object : OnItemClickListener<User> {
        @SuppressLint("RestrictedApi")
        override fun onItemClick(view: View?, position: Int, t: User?) {

            val i = Intent(view?.context, UserDetailActivity::class.java)

            i.putExtra(UserDetailActivity.Param_User, t)

            view.let {

                val activity = it!!.context as Activity
                val transitionActivityOptions =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                                it!!.findViewById(R.id.nameTV), UserDetailActivity.Transition_Name_User_Name)


                activity!!.startActivityForResult(i, Request_Code, transitionActivityOptions.toBundle())
            }
        }

        override fun onItemLongClick(view: View?, position: Int, t: User?) {
            showSnackBar(view!!, "您确定要删除 \"${t?.name}\" 该用户", "确定", View.OnClickListener {

                try {
                    showLoading((view?.context as MainActivity).supportFragmentManager)
                } catch (e: Exception) {
                    //context强制activity可能会除错
                    e.printStackTrace()
                }

                deleteUser(t!!)
            })
        }
    })


    private val dataBase: LocalData by lazy {
        LocalData(applicition)
    }

    override fun subscribe(t: MainActivity) {
        if (dataChange == null) {
            log("init mUsers")
            dataChange = MutableLiveData()

            //观察mUsers变化
            dataChange?.observe(t, Observer<Boolean> {
                //监听到变化后，更新用户列表
                it.let {
                    showLoading(t.supportFragmentManager)
                    queryUser()
                }
            })
        }

        dataChange.let {
            it!!.value = true
        }
    }

    /**
     * 查询所有用户
     */
    private fun queryUser() {
        dataBase.queryUsers()
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

    /**
     * 删除用户
     */
    private fun deleteUser(user: User) {
        dataBase.deleteUser(user)
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
     * 添加用户，在Button中触发
     */
    fun addUser(view: View) {
        if (TextUtils.isEmpty(name.get())) {
            showSnackBar(view, "name is null", null, null)
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

        dataBase.insertUser(user)
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

}