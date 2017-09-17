package br.com.liveo.mvp.base

import br.com.liveo.mvp.main.MainView

/**
 * Created by rudsonlima on 8/29/17.
 */

interface BaseView<T> : MainView {
    fun onLoading(isLoading: Boolean)
    fun onError(error: String)
}
