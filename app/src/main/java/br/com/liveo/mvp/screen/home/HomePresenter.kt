package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.base.BasePresenter
import br.com.liveo.mvp.base.BaseView
import br.com.liveo.mvp.main.MainView
import br.com.liveo.mvp.model.domain.UserResponse
import br.com.liveo.mvp.util.scheduler.BaseScheduler
import javax.inject.Inject

/**
 * Created by rudsonlima on 8/29/17.
 */
class HomePresenter @Inject
constructor(private val mHomeInteractor: HomeInteractor, scheduler: BaseScheduler) : BasePresenter<HomeContract.View>(scheduler), HomeContract.Presenter {

    private var mView: HomeContract.View? = null

    override fun fetchUsers() {
        mView!!.onLoading(true)

        mHomeInteractor.fetchUsers(mView!!.page)
                .subscribeOn(this.schedulerProvider.io())
                .observeOn(this.schedulerProvider.ui())
                .subscribe(
                        { user ->
                            sucess(user)
                        },
                        { error ->
                            (mView as HomeContract.View).onError(error.message!!)
                        }
                )
    }

    private fun error(error: String) {
        mView!!.onLoading(false)
        mView!!.onError(error)
    }

    private fun sucess(userResponse: UserResponse) {
        mView!!.onLoading(false)
        mView!!.onUserResponse(userResponse)
    }

    override fun attach(view: HomeContract.View) {
        this.mView = view
    }

    override fun detachView() {
        this.mView = null
    }

    override fun getView(): BaseView<*> = this.mView!!
}
