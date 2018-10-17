package com.wzx.mvvmdemo.utils

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/17
 * 更新时间：
 * 更新内容：
 */
class DateConverter {

    @TypeConverter
    open fun toTimeStamp(date: Date?) = date?.time

    @TypeConverter
    open fun toDate(timeStamp: Long?): Date? {
        return if (timeStamp == null) null else Date(timeStamp)
    }
}