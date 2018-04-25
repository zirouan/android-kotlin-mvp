package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.base.BasePresenter
import br.com.liveo.mvp.util.scheduler.BaseScheduler
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

    override fun fetchUsers() {
        this.mView?.let {
            it.onLoading(true)

            this.addDisposable(this.mInteractor.fetchUsers(it.page)
                    .subscribeOn(this.scheduler.io())
                    .observeOn(this.scheduler.ui())
                    .subscribe({ response ->
                        it.onLoading(false)
                        it.onUserResponse(response)
                    }, { error ->
                        it.onLoading(false)
                        it.onError(error)
                    }))
        }
    }
}
