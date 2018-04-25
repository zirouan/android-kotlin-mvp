package br.com.liveo.mvp.di.modules


import android.content.Context
import br.com.liveo.mvp.BuildConfig
import br.com.liveo.mvp.data.local.Preferences
import br.com.liveo.mvp.data.local.PreferencesHelper
import br.com.liveo.mvp.data.remote.ApiHelper
import br.com.liveo.mvp.data.remote.EndPoint
import br.com.liveo.mvp.data.remote.EndPointHelper
import br.com.liveo.mvp.data.remote.interceptor.RequestInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by rudsonlima on 9/4/17.
 */
@Module
class HelperModule(private val mContext: WeakReference<Context>) {

    companion object {
        private val CONNECT_TIMEOUT_IN_MS = 300000
    }

    @Provides
    @Singleton
    internal fun provideContext(): WeakReference<Context> {
        return mContext
    }

    @Provides
    @Singleton
    internal fun proviveRequestInterceptor(preferencesHelper: PreferencesHelper): Interceptor {
        return RequestInterceptor(preferencesHelper)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(requestInterceptor: RequestInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_IN_MS.toLong(), TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(requestInterceptor)
                .build()
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Singleton
    @Provides
    internal fun provideEndPoint(retrofit: Retrofit): EndPoint {
        return retrofit.create<EndPoint>(EndPoint::class.java)
    }

    @Singleton
    @Provides
    internal fun provideEndPointHelper(endPoint: EndPoint): EndPointHelper {
        return ApiHelper(endPoint)
    }

    @Provides
    @Singleton
    internal fun providePreferencesHelper(context: WeakReference<Context>): PreferencesHelper {
        return Preferences(context)
    }
}
