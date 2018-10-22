package com.wzx.mvvmdemo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.wzx.mvvmdemo.view.dialog.LoadDialog

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/19
 * 更新时间：
 * 更新内容：
 */
abstract class BaseViewModel<T : LifecycleOwner>(application: Application) : AndroidViewModel(application), IViewModel<T> {

    /**
     * 等待弹窗
     */
    private val loadDialog: LoadDialog by lazy {
        LoadDialog()
    }

    /**
     * 显示LoadDialog
     * @param fragmentManager
     */
    protected fun showLoading(fragmentManager: FragmentManager) {
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
    protected fun dimissLoading() {
        loadDialog.let {
            //当LoadDialog显示时，才触发消失
            if (it.isVisible) it.dismiss()
        }
    }

    /**
     * 关闭软键盘
     */
    protected fun closeSoftBoard(context: Context) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.let {
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }
    }

    /**
     * @param view
     * @param msg 消息
     * @param action 行为描述
     * @param listener
     */
    protected fun showSnackBar(view: View, msg: String, action: String?, listener: View.OnClickListener?) {
        val snackBar = Snackbar.make(view, msg, if (TextUtils.isEmpty(action)) Snackbar.LENGTH_SHORT else Snackbar.LENGTH_LONG)
        if (!TextUtils.isEmpty(action) && listener != null) {
            snackBar.setAction(action, listener)
        }
        snackBar.show()
    }

    /**
     * 输出日志
     */
    protected fun log(msg: String) {
        Log.i(this.javaClass.simpleName, msg)
    }
}