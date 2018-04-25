package br.com.liveo.mvp.screen.home.di

import android.support.annotation.NonNull
import br.com.liveo.mvp.data.remote.endpoint.EndPointHelper
import br.com.liveo.mvp.di.scope.ActivityScoped
import br.com.liveo.mvp.screen.home.HomeContract
import br.com.liveo.mvp.screen.home.HomeRepository
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
    fun provideHomeRepository(endPointHelper: EndPointHelper): HomeContract.Repository =
            HomeRepository(endPointHelper)

    @Provides
    @ActivityScoped
    fun provideHomePresenter(@NonNull repository: HomeContract.Repository,
                             @NonNull scheduler: BaseScheduler): HomeContract.Presenter =
            HomePresenter(repository, scheduler)

    @Provides
    fun provideScheduleProvider(): BaseScheduler = SchedulerProvider.instance
}
