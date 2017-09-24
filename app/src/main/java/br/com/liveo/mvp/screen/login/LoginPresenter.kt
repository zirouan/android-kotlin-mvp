package br.com.liveo.mvp.screen.login

import br.com.liveo.mvp.base.BasePresenter
import br.com.liveo.mvp.base.BaseView
import br.com.liveo.mvp.util.scheduler.BaseScheduler
import javax.inject.Inject

/**
 * Created by rudsonlima on 8/29/17.
 */
class LoginPresenter @Inject
constructor(scheduler: BaseScheduler) : BasePresenter<LoginContract.View>(scheduler), LoginContract.Presenter {

    private var mView: LoginContract.View? = null


    override fun attach(view: LoginContract.View) {
        this.mView = view
    }

    override fun detachView() {
        this.mView = null
    }

    override fun getView(): LoginContract.View? = this.mView

    override fun toDoLogin() {

    }
}
