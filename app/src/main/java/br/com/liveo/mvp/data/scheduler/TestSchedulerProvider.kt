package br.com.liveo.mvp.data.scheduler

import br.com.liveo.mvp.base.BaseScheduler
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider(private val mTestScheduler: TestScheduler) : BaseScheduler {

    override fun computation(): Scheduler = mTestScheduler

    override fun io(): Scheduler = mTestScheduler

    override fun ui(): Scheduler = mTestScheduler
}
