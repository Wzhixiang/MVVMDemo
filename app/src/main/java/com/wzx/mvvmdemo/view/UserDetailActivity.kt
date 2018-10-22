package com.wzx.mvvmdemo.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.transition.Slide
import android.view.Gravity
import com.wzx.mvvmdemo.R
import com.wzx.mvvmdemo.databinding.ActivityUserDetailBinding
import com.wzx.mvvmdemo.model.bean.User
import com.wzx.mvvmdemo.viewmodel.UserDetailViewModel

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/19
 * 更新时间：
 * 更新内容：
 */
class UserDetailActivity : AppCompatActivity() {

    companion object {
        const val Request_Code = 1100
        const val Response_Code = 1200
        const val Param_User = "user"
        const val Transition_Name_User_Name = "user_name"
    }

    private val viewModel: UserDetailViewModel by lazy {
        //获取ViewModel
        ViewModelProviders.of(this).get(UserDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //使用DataBindingUtil设定布局
        val dataBinding = DataBindingUtil.setContentView<ActivityUserDetailBinding>(this, R.layout.activity_user_detail)

        setUpWindowAnimation()

        val param = intent.getSerializableExtra(Param_User)
        if (param != null) {
            viewModel.user = param as User
        }

        dataBinding.viewModel = viewModel

        //设置shared View
        ViewCompat.setTransitionName(dataBinding.nameTV, Transition_Name_User_Name)

        viewModel.let {
            it.subscribe(this@UserDetailActivity)
        }
    }

    /**
     * 设置窗口动画
     */
    private fun setUpWindowAnimation() {
        val enterTransition = Slide()
        enterTransition.duration = 250
        enterTransition.slideEdge = Gravity.RIGHT

        window.enterTransition = enterTransition
    }

    override fun finish() {
        if (viewModel.change) {
            setResult(Response_Code)
        }
        super.finish()
    }

}