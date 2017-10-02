package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.base.BasePresenter
import br.com.liveo.mvp.model.domain.UserResponse
import br.com.liveo.mvp.util.scheduler.BaseScheduler
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by rudsonlima on 8/29/17.
 */
class HomePresenter @Inject
constructor(val mInteractor: HomeContract.Interactor, scheduler: BaseScheduler) : BasePresenter<HomeContract.View>(scheduler), HomeContract.Presenter {

    var mView: HomeContract.View? = null

    override fun fetchUsers() {
        this.mInteractor.fetchUsers(this.mView!!.page).subscribeOn(this.schedulerProvider.io())
                .observeOn(this.schedulerProvider.ui())
                .subscribe(object : Observer<UserResponse> {
                    override fun onSubscribe(@io.reactivex.annotations.NonNull d: Disposable) {
                        mView!!.onLoading(true)
                    }

                    override fun onNext(@io.reactivex.annotations.NonNull response: UserResponse) {
                        mView!!.onLoading(false)
                        mView!!.onUserResponse(response)
                    }

                    override fun onError(@io.reactivex.annotations.NonNull error: Throwable) {
                        mView!!.onLoading(false)
                        mView!!.onError(error)
                    }

                    override fun onComplete() {

                    }
                })
    }

    override fun attach(view: HomeContract.View) {
        this.mView = view
    }

    override fun detachView() {
        this.mView = null
    }

    override fun getView(): HomeContract.View? = this.mView
}
