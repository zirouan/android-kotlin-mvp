package br.com.liveo.mvp.screen.home.di

import br.com.liveo.mvp.di.scope.ActivityScoped
import br.com.liveo.mvp.screen.home.HomeContract
import br.com.liveo.mvp.screen.home.HomeInteractor
import br.com.liveo.mvp.screen.home.HomePresenter
import br.com.liveo.mvp.data.source.remote.ApiEndPoint
import br.com.liveo.mvp.util.scheduler.BaseScheduler
import br.com.liveo.mvp.util.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides

/**
 * Created by rudsonlima on 9/2/17.
 */
@Module
class HomeModule {

    @Provides
    @ActivityScoped
    internal fun provideHomeInteractor(apiEndPoint: ApiEndPoint): HomeInteractor =
            HomeInteractor(apiEndPoint)

    @Provides
    @ActivityScoped
    internal fun provideHomePresenter(homeInteractor: HomeInteractor,
                                      scheduler: BaseScheduler): HomeContract.Presenter =
            HomePresenter(homeInteractor, scheduler)

    @Provides
    internal fun provideScheduleProvider(): BaseScheduler = SchedulerProvider.instance
}
