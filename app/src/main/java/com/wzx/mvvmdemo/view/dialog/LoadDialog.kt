package com.wzx.mvvmdemo.view.dialog

import android.app.DialogFragment
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wzx.mvvmdemo.R
import com.wzx.mvvmdemo.databinding.DialogLoadBinding
import com.wzx.mvvmdemo.viewmodel.LoadViewModel

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/17
 * 更新时间：
 * 更新内容：
 */
class LoadDialog : AppCompatDialogFragment() {

    companion object {
        const val TAG = "LoadDialog"
    }


    private val viewModel: LoadViewModel by lazy {
        ViewModelProviders.of(this).get(LoadViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val dataBinding = DataBindingUtil.inflate<DialogLoadBinding>(inflater, R.layout.dialog_load, container, false)

        dataBinding.viewModel = viewModel

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.subscribe(this)
    }
}