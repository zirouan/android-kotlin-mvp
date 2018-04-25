package br.com.liveo.mvp.di.components

import br.com.liveo.mvp.di.modules.HelperModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by rudsonlima on 9/2/17.
 */

@Singleton
@Component(modules = [(HelperModule::class)])
interface ApplicationComponent : BaseApplicationComponent
