package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.base.BasePresenter
import br.com.liveo.mvp.model.domain.UserResponse
import br.com.liveo.mvp.util.scheduler.BaseScheduler
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * This class makes module for {@link HomeModule}
 *
 * @author Rudson Lima
 * @since 10/02/17
 */

class HomePresenter @Inject
constructor(val mInteractor: HomeContract.Interactor, scheduler: BaseScheduler) :
        BasePresenter<HomeContract.View>(scheduler), HomeContract.Presenter {

    var mView: HomeContract.View? = null

    override fun fetchUsers() {
        this.mView?.let {
            it.onLoading(true)

            this.mInteractor.fetchUsers(it.page).subscribeOn(this.schedulerProvider.io())
                    .observeOn(this.schedulerProvider.ui())
                    .subscribe({ response ->
                        it.onLoading(false)
                        it.onUserResponse(response)
                    }, { error ->
                        it.onLoading(false)
                        it.onError(error)
                    })
        }
    }

    override fun attach(view: HomeContract.View) {
        this.mView = view
    }

    override fun detachView() {
        this.mView = null
    }

    override fun getView(): HomeContract.View? = this.mView
}
