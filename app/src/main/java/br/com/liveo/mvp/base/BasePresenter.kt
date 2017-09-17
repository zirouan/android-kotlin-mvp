package br.com.liveo.mvp.base


import br.com.liveo.mvp.main.MainPresenter
import br.com.liveo.mvp.main.MainView
import br.com.liveo.mvp.util.scheduler.BaseScheduler

/**
 * Created by rudsonlima on 8/29/17.
 */

abstract class BasePresenter<T : MainView>(protected val schedulerProvider: BaseScheduler) : MainPresenter<T>