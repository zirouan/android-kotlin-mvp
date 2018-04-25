package br.com.liveo.mvp.base


import br.com.liveo.mvp.main.MainPresenter
import br.com.liveo.mvp.main.MainView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by rudsonlima on 8/29/17.
 */

abstract class BasePresenter<T : MainView>(protected val scheduler: BaseScheduler) : MainPresenter<T> {


    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        this.mCompositeDisposable.add(disposable)
    }

    var mView: T? = null

    override fun detachView() {
        this.clearDisposables()
        this.mView = null
    }

    override fun attachView(view: T) {
        this.mView = view
    }

    protected fun clearDisposables() {
        this.mCompositeDisposable.clear()
    }
}