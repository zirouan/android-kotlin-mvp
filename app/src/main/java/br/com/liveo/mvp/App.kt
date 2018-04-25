package br.com.liveo.mvp

import android.app.Application
import br.com.liveo.mvp.di.components.BaseApplicationComponent
import br.com.liveo.mvp.di.components.DaggerApplicationComponent
import br.com.liveo.mvp.di.modules.HelperModule
import java.lang.ref.WeakReference

/**
 * Created by rudsonlima on 8/31/17.
 */

class App : Application() {

    companion object {
        @JvmStatic
        lateinit var application: BaseApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        application = DaggerApplicationComponent.builder()
                .helperModule(HelperModule(WeakReference(this))).build()
    }
}
