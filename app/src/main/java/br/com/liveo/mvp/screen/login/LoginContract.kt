package br.com.liveo.mvp.screen.login

import br.com.liveo.mvp.base.BaseView
import br.com.liveo.mvp.main.MainPresenter

/**
 * Created by rudsonlima on 8/29/17.
 */

interface LoginContract {

    interface View : BaseView<Presenter> {

        val email: String
        val password: String
        fun onLoginSuccess()
        fun onLoginFailed(exception: Throwable)
    }

    interface Presenter : MainPresenter<View> {
        fun toDoLogin()
    }
}
