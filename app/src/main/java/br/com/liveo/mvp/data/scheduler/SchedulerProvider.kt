package br.com.liveo.mvp.data.scheduler

import br.com.liveo.mvp.base.BaseScheduler
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by rudsonlima on 8/29/17.
 */

class SchedulerProvider private constructor() : BaseScheduler {

    override fun io(): Scheduler = Schedulers.io()
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun computation(): Scheduler = Schedulers.computation()

    companion object {

        private var INSTANCE: SchedulerProvider? = null

        val instance: SchedulerProvider
            @Synchronized get() {
                if (INSTANCE == null) {
                    INSTANCE = SchedulerProvider()
                }
                return INSTANCE as SchedulerProvider
            }
    }
}
