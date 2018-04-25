package br.com.liveo.mvp.di.components

import br.com.liveo.mvp.di.modules.HelperTestModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by rudsonlima on 9/2/17.
 */

@Singleton
@Component(modules = [(HelperTestModule::class)])
interface ApplicationTestComponent : BaseApplicationComponent
