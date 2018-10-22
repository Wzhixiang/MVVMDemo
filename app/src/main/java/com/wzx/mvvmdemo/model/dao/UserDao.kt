package com.wzx.mvvmdemo.model.dao

import android.arch.persistence.room.*
import com.wzx.mvvmdemo.model.bean.User

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/17
 * 更新时间：
 * 更新内容：
 */
@Dao
interface UserDao {

    @Query("select * from user")
    fun queryAll(): List<User>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    @Delete
    fun delectUser(user: User): Int

    @Update
    fun updateUser(user: User): Int

}