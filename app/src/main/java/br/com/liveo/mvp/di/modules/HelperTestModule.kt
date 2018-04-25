package br.com.liveo.mvp.di.modules


import android.content.Context
import br.com.liveo.mvp.data.local.Preferences
import br.com.liveo.mvp.data.local.PreferencesHelper
import br.com.liveo.mvp.data.local.endpoint.EndPointMocked
import br.com.liveo.mvp.data.remote.ApiHelper
import br.com.liveo.mvp.data.remote.endpoint.EndPoint
import br.com.liveo.mvp.data.remote.endpoint.EndPointHelper
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference
import javax.inject.Singleton

/**
 * Created by rudsonlima on 9/4/17.
 */
@Module
class HelperTestModule(context: WeakReference<Context>?) {

    private val endPoint: EndPoint
    lateinit var preferencesHelper: PreferencesHelper

    constructor() : this(null)

    init {
        this.endPoint = EndPointMocked()

        context?.let {
            preferencesHelper = Preferences(it)
        }
    }

    @Singleton
    @Provides
    internal fun providePreferencesHelper(): PreferencesHelper {
        return this.preferencesHelper
    }

    @Singleton
    @Provides
    internal fun provideEndPoint(): EndPoint {
        return this.endPoint
    }

    @Singleton
    @Provides
    internal fun provideEndPointHelper(endPoint: EndPoint): EndPointHelper {
        return ApiHelper(endPoint)
    }
}
