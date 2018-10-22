package com.wzx.mvvmdemo.utils

import android.databinding.BindingConversion
import android.graphics.drawable.ColorDrawable
import android.support.annotation.CheckResult
import android.support.annotation.ColorInt
import java.text.SimpleDateFormat
import java.util.*

/**
 * 描述：DataBinding 类型转换
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/16
 * 更新时间：
 * 更新内容：
 */


/**
 * 将日期转换成年-月-日 时:分:秒
 * @param date
 */
@CheckResult(suggest = "将日期转换成年-月-日 时:分:秒，是字符串格式")
@BindingConversion
fun dateToString(date: Date?) = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date ?: Date())

/**
 * 将颜色转换成Drawable
 */
@BindingConversion
fun colorIdToDrawable(@ColorInt colorId: Int?): ColorDrawable {
    colorId.let {
        return ColorDrawable(colorId!!)
    }
}

