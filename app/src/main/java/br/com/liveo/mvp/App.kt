package br.com.liveo.mvp

import android.app.Application
import br.com.liveo.mvp.di.components.ApplicationComponent
import br.com.liveo.mvp.di.components.DaggerApplicationComponent
import br.com.liveo.mvp.di.modules.NetworkModule

/**
 * Created by rudsonlima on 8/31/17.
 */

class App : Application() {

    companion object {
        @JvmStatic lateinit var application: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        application = DaggerApplicationComponent.builder()
                .networkModule(NetworkModule()).build()
    }
}
