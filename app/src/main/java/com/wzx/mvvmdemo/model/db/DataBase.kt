package com.wzx.mvvmdemo.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.wzx.mvvmdemo.model.bean.User
import com.wzx.mvvmdemo.model.dao.UserDao
import com.wzx.mvvmdemo.utils.DateConverter

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/17
 * 更新时间：
 * 更新内容：
 */

@Database(entities = [User::class], version = 1)
@TypeConverters(value = [DateConverter::class])
abstract class DataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val DataBaseName = "Local.db"

        @Volatile
        private var database: DataBase? = null

        /**
         * 获取DataBase，当DataBase未被初始化时，先初始化
         */
        fun getInstance(context: Context) = database ?: synchronized(this) {
            database ?: createDataBase(context).also {
                database = it
            }
        }

        /**
         * 创建数据库
         */
        private fun createDataBase(context: Context): DataBase =
                Room.databaseBuilder(context.applicationContext, DataBase::class.java, DataBaseName)
                        .build()
    }
}