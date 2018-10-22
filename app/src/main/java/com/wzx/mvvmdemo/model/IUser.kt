package com.wzx.mvvmdemo.model

import android.arch.persistence.room.Update
import android.support.annotation.WorkerThread
import com.wzx.mvvmdemo.model.bean.User
import io.reactivex.Observable

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/17
 * 更新时间：
 * 更新内容：
 */
interface IUser {

    @WorkerThread
    fun insertUser(user: User): Observable<Boolean>

    @WorkerThread
    fun queryUsers(): Observable<List<User>>

    @Update
    fun updateUser(user: User): Observable<Boolean>

    @WorkerThread
    fun deleteUser(user: User): Observable<Boolean>
}