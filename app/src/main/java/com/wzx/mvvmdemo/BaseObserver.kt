package com.wzx.mvvmdemo

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/10/17
 * 更新时间：
 * 更新内容：
 */
abstract class BaseObserver<T> : Observer<T> {

    private var disposable: Disposable? = null

    abstract fun onSuccess(t: T)

    abstract fun onFail(msg: String?)

    override fun onComplete() {
        unSubscribe()
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        onFail(e.message)
        unSubscribe()
    }

    private fun unSubscribe() {
        if (disposable != null && !disposable!!.isDisposed) {
            disposable!!.dispose()
        }
    }
}