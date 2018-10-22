package com.wzx.mvvmdemo.model

import android.content.Context
import android.support.annotation.NonNull
import android.support.annotation.WorkerThread
import com.wzx.mvvmdemo.model.bean.User
import com.wzx.mvvmdemo.model.db.DataBase
import io.reactivex.Observable

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/17
 * 更新时间：
 * 更新内容：
 */
class LocalData(@NonNull var context: Context) : IUser {

    private val dataBase: DataBase = DataBase.getInstance(context)

    @WorkerThread
    override fun queryUsers(): Observable<List<User>> {
        return Observable.create({
            val list = dataBase.userDao().queryAll()
            if (list == null) it.onError(Throwable("暂无用户数据")) else {
                it.onNext(list)
                it.onComplete()
            }
        })
    }

    @WorkerThread
    override fun insertUser(user: User): Observable<Boolean> {
        return Observable.create({
            val state = dataBase.userDao().insertUser(user)
            if (state == null) it.onError(Throwable("写入数据失败")) else {
                if (state.toInt() > 0) {
                    it.onNext(true)
                    it.onComplete()
                } else {
                    it.onError(Throwable("写入数据失败"))
                }
            }
        })
    }

    override fun deleteUser(user: User): Observable<Boolean> {
        return Observable.create({
            val state = dataBase.userDao().delectUser(user)
            if (state == null) it.onError(Throwable("删除失败")) else {
                if (state > 0) {
                    it.onNext(true)
                    it.onComplete()
                } else {
                    it.onError(Throwable("删除失败"))
                }
            }
        })
    }

    override fun updateUser(user: User): Observable<Boolean> {
        return Observable.create({
            val state = dataBase.userDao().updateUser(user)
            if (state == null) it.onError(Throwable("更新失败")) else {
                if (state > 0) {
                    it.onNext(true)
                    it.onComplete()
                } else {
                    it.onError(Throwable("更新失败"))
                }
            }
        })
    }
}