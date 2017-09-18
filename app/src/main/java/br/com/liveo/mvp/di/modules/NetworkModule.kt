package br.com.liveo.mvp.di.modules


import br.com.liveo.mvp.BuildConfig
import br.com.liveo.mvp.data.source.remote.ApiEndPoint
import br.com.liveo.mvp.data.source.remote.RequestInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by rudsonlima on 9/4/17.
 */
@Module
class NetworkModule {
    companion object {
        private val CONNECT_TIMEOUT_IN_MS = 30000
    }

    @Provides
    @Singleton
    internal fun requestInterceptor(interceptor: RequestInterceptor): Interceptor = interceptor

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
    internal fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Singleton
    @Provides
    internal fun apiEndPoint(retrofit: Retrofit): ApiEndPoint =
            retrofit.create(ApiEndPoint::class.java)
}
