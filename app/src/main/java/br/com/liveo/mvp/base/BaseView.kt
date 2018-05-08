package br.com.liveo.mvp.base

import br.com.liveo.mvp.main.MainView

/**
 * Created by rudsonlima on 8/29/17.
 */

interface BaseView<T> : MainView {
    fun onLoading(loading: Boolean)
    fun onError(error: Throwable?)
}
