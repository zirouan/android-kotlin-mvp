package br.com.liveo.mvp.main

import br.com.liveo.mvp.screen.home.HomeContract

/**
 * Created by rudsonlima on 8/29/17.
 */

interface MainPresenter<T> {
    fun attachView(view: T)
    fun detachView()

    val view: T?
}
