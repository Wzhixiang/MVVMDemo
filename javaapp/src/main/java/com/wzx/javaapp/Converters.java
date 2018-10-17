package com.wzx.javaapp;

import android.databinding.BindingConversion;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：
 * <p>
 * 创建人： Administrator
 * 创建时间： 2018/10/17
 * 更新时间：
 * 更新内容：
 */

public class Converters {

    /**
     * 将日期转换成年-月-日 时:分:秒
     *
     * @param date
     */
    @CheckResult(suggest = "将日期转换成年-月-日 时:分:秒，是字符串格式")
    @BindingConversion
    public static String dateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 将颜色转换成Drawable
     *
     * @param colorId
     */
    @CheckResult(suggest = "将资源中的颜色转换成Drawable")
    @BindingConversion
    public static ColorDrawable colorIdToDrawable(@ColorInt int colorId) {
        return new ColorDrawable(colorId);
    }
}
