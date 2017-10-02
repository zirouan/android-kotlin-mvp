package br.com.liveo.mvp.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ivan on 8/20/2017.
 */
@Singleton
class RequestInterceptor @Inject
constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("Content-Type", "application/json")
                .build()

        val request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
