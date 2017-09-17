package br.com.liveo.mvp.util.scheduler

/**
 * Created by rudsonlima on 8/29/17.
 */
object InjectionScheduler {
    fun schedulerProvider(): BaseScheduler = SchedulerProvider.instance
}
