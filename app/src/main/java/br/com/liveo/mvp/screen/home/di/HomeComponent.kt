package br.com.liveo.mvp.screen.home.di

import br.com.liveo.mvp.di.scope.ActivityScoped
import br.com.liveo.mvp.screen.home.HomeActivity
import dagger.Subcomponent

/**
 * This class makes Component for {@link ApplicationComponent}
 *
 * @author Rudson Lima
 * @since 10/02/17
 */
@ActivityScoped
@Subcomponent(modules = [(HomeModule::class)])
interface HomeComponent {
    fun inject(homeActivity: HomeActivity)
}
