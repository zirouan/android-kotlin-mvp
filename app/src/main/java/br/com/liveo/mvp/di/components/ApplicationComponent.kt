package br.com.liveo.mvp.di.components

import br.com.liveo.mvp.di.modules.NetworkModule
import br.com.liveo.mvp.screen.home.di.HomeComponent
import br.com.liveo.mvp.screen.home.di.HomeModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by rudsonlima on 9/2/17.
 */

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface ApplicationComponent {
    fun module(detailsModule: HomeModule): HomeComponent
}
