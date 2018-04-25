package br.com.liveo.mvp.screen.home.di

import android.support.annotation.NonNull
import br.com.liveo.mvp.data.remote.endpoint.EndPointHelper
import br.com.liveo.mvp.di.scope.ActivityScoped
import br.com.liveo.mvp.screen.home.HomeContract
import br.com.liveo.mvp.screen.home.HomeInteractor
import br.com.liveo.mvp.screen.home.HomePresenter
import br.com.liveo.mvp.base.BaseScheduler
import br.com.liveo.mvp.data.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides

/**
 * This class makes Subcomponent for {@link HomeComponent}
 *
 * @author Rudson Lima
 * @since 10/02/17
 */

@Module
class HomeModule {

    @Provides
    @ActivityScoped
    fun provideHomeInteractor(endPointHelper: EndPointHelper): HomeContract.Interactor =
            HomeInteractor(endPointHelper)

    @Provides
    @ActivityScoped
    fun provideHomePresenter(@NonNull interactor: HomeContract.Interactor,
                             @NonNull scheduler: BaseScheduler): HomeContract.Presenter =
            HomePresenter(interactor, scheduler)

    @Provides
    fun provideScheduleProvider(): BaseScheduler = SchedulerProvider.instance
}
