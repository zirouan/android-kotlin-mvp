package br.com.liveo.mvp.screen.home.di

import br.com.liveo.mvp.di.scope.ActivityScoped
import br.com.liveo.mvp.screen.home.HomeActivity
import dagger.Subcomponent

/**
 * Created by rudsonlima on 9/4/17.
 */
@ActivityScoped
@Subcomponent(modules = arrayOf(HomeModule::class))
interface HomeComponent {
    fun inject(homeActivity: HomeActivity)
}
