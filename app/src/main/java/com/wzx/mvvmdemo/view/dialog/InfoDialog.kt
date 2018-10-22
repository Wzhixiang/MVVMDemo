package com.wzx.mvvmdemo.view.dialog

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wzx.mvvmdemo.R
import com.wzx.mvvmdemo.databinding.DialogInfoBinding
import com.wzx.mvvmdemo.viewmodel.InfoViewModel
import kotlinx.android.synthetic.main.dialog_info.*

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/19
 * 更新时间：
 * 更新内容：
 */
class InfoDialog : AppCompatDialogFragment() {

    companion object {
        const val TAG = "LoadDialog"
        const val Param_Info = "info"

        fun newInstanc(arg: Bundle): InfoDialog {
            val dialog = InfoDialog()
            dialog.arguments = arg
            return dialog
        }
    }

    private var mListener: OnInfoChangeListener? = null

    private val viewModel: InfoViewModel by lazy {
        ViewModelProviders.of(this).get(InfoViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val dataBinding = DataBindingUtil.inflate<DialogInfoBinding>(inflater, R.layout.dialog_info, container, false)

        dataBinding.viewModel = viewModel

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog.setCanceledOnTouchOutside(false)

        viewModel.info.set(arguments?.getString(Param_Info))

        dialog.actionBar?.title = "修改用户名"

        //cancelTV是通过kotlin特有的方式获取view，-->import kotlinx.android.synthetic.main.dialog_info.*
        cancelTV.setOnClickListener {
            dismiss()
        }

        sureTV.setOnClickListener {
            mListener.let {
                it!!.onChanged(viewModel.info.get())
            }
            dismiss()
        }
    }

    fun addOnInfoChangeListener(listener: OnInfoChangeListener) {
        this.mListener = listener
    }
}