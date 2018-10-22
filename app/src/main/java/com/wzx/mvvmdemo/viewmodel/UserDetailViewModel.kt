package com.wzx.mvvmdemo.viewmodel

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.os.Bundle
import android.support.annotation.NonNull
import android.view.View
import com.wzx.mvvmdemo.BaseObserver
import com.wzx.mvvmdemo.model.LocalData
import com.wzx.mvvmdemo.model.bean.User
import com.wzx.mvvmdemo.view.UserDetailActivity
import com.wzx.mvvmdemo.view.dialog.InfoDialog
import com.wzx.mvvmdemo.view.dialog.OnInfoChangeListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/19
 * 更新时间：
 * 更新内容：
 */
class UserDetailViewModel(application: Application) : BaseViewModel<UserDetailActivity>(application) {

    var user: User? = null

    var change:Boolean = false

    private val data: LocalData by lazy {
        LocalData(application)
    }

    override fun subscribe(@NonNull activity: UserDetailActivity) {

    }

    fun nameClick(view: View) {
        showInfoDialog(view.context)
    }

    private fun showInfoDialog(context: Context): InfoDialog {
        val arg = Bundle()
        arg.putString(InfoDialog.Param_Info, user?.name)
        val dialog = InfoDialog.newInstanc(arg)

        dialog.addOnInfoChangeListener(object : OnInfoChangeListener {
            override fun onChanged(info: String) {
                showLoading((context as UserDetailActivity).supportFragmentManager)

                updateUser(info)
            }
        })

        try {
            dialog.show((context as UserDetailActivity).supportFragmentManager, InfoDialog.TAG)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dialog
    }

    private fun updateUser(name: String) {
        user.let {
            val copyUser = it!!.copy()
            copyUser.name = name
            data.updateUser(copyUser)
                    .delay(1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : BaseObserver<Boolean>() {
                        override fun onSuccess(t: Boolean) {
                            it!!.name = name
                            dimissLoading()
                            change = true
                        }

                        override fun onFail(msg: String?) {
                            log(msg!!)
                            dimissLoading()
                        }
                    })
        }

    }
}
