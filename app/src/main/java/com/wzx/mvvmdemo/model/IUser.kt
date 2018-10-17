package com.wzx.mvvmdemo.model

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
    fun addUser(user: User): Observable<Boolean>

    @WorkerThread
    fun getUsers(): Observable<List<User>>

}