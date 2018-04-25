package br.com.liveo.mvp.main

/**
 * Created by rudsonlima on 8/29/17.
 */

interface MainPresenter<T> {
    fun attachView(view: T)
    fun detachView()
}
