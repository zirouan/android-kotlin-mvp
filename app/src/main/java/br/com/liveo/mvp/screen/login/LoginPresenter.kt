package br.com.liveo.mvp.screen.login

import br.com.liveo.mvp.base.BasePresenter
import br.com.liveo.mvp.base.BaseScheduler
import javax.inject.Inject

/**
 * Created by rudsonlima on 8/29/17.
 */
class LoginPresenter @Inject
constructor(scheduler: BaseScheduler) : BasePresenter<LoginContract.View>(scheduler), LoginContract.Presenter {

    override fun toDoLogin() {

    }
}
