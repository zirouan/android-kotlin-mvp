package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.base.BasePresenter
import br.com.liveo.mvp.base.BaseScheduler
import javax.inject.Inject

/**
 * This class makes module for {@link HomeModule}
 *
 * @author Rudson Lima
 * @since 10/02/17
 */

class HomePresenter @Inject
constructor(val repository: HomeContract.Repository, scheduler: BaseScheduler) :
        BasePresenter<HomeContract.View>(scheduler), HomeContract.Presenter {

    override val view: HomeContract.View?
        get() = super.view

    override fun fetchUsers() {
        this.mView?.let {
            it.onLoading(true)

            this.addDisposable(this.repository.fetchUsers(it.page)
                    .subscribeOn(this.scheduler.io())
                    .observeOn(this.scheduler.ui())
                    .subscribe({ response ->
                        it.onLoading(false)

                        if (response != null)
                            it.onUserResponse(response) else it.onError(null)
                    }, { error ->
                        it.onLoading(false)
                        it.onError(error)
                    }))
        }
    }
}
