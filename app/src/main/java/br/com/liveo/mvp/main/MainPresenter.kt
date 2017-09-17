package br.com.liveo.mvp.main

import br.com.liveo.mvp.base.BaseView
import br.com.liveo.mvp.model.domain.UserResponse
import io.reactivex.Observable

/**
 * Created by rudsonlima on 8/29/17.
 */

interface MainPresenter<T> {
    fun attach(view: T)
    fun detachView()
    fun getView(): BaseView<*>
}
