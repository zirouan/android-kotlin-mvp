package br.com.liveo.mvp.data.scheduler

import br.com.liveo.mvp.base.BaseScheduler

/**
 * Created by rudsonlima on 8/29/17.
 */
object InjectionScheduler {
    fun schedulerProvider(): BaseScheduler = SchedulerProvider.instance
}
