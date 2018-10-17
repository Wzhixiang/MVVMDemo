package com.wzx.mvvmdemo.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.wzx.mvvmdemo.viewmodel.MainViewModel
import com.wzx.mvvmdemo.R
import com.wzx.mvvmdemo.databinding.ActivityMainBinding

/**
 * MainActivity只是负责引用布局样式
 *
 * @see MainViewModel 负责所有逻辑处理、以及数据交互
 */
class MainActivity : AppCompatActivity() {

    private val TAG = "Activity MainViewModel"

    private val viewModel: MainViewModel by lazy {
        //获取ViewModel
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //使用DataBindingUtil设定布局
        val dataBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        dataBinding.viewModel = viewModel

        viewModel.let {
            it.subscribe(this@MainActivity)
        }


    }

    override fun onResume() {
        super.onResume()

        Log.i(TAG, "onResume")
    }

    override fun onPause() {
        Log.i(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.i(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy")
        super.onDestroy()
    }
}
