package com.taz.bookmarkapp.helpers

import android.content.Context
import com.google.gson.GsonBuilder
import com.taz.bookmarkapp.mvp.BaseActivity
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    lateinit var retroClient: Retrofit
    private const val BASE_URL = "http://tazril.pythonanywhere.com/"


    fun instantiate(context: Context): ApiClient {
        val cache = Cache(context.cacheDir, 5 * 1024 * 1024)     //5 MB
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().cache(cache)
            .addInterceptor { chain ->
                val oldRequest = chain.request()
                var newRequest: Request? = null
                if (!(context as BaseActivity).isConnected()) {
                    newRequest = oldRequest.newBuilder()
                        .addHeader("Cache-Control", "public, only-if-cached, max-stale=${60 * 60 * 24 * 30}") //1 MONTH
                        .build()
                }
                chain.proceed(newRequest ?: oldRequest)
            }
            .addInterceptor(interceptor)
            .build()
        val gson = GsonBuilder().serializeNulls().create()
        retroClient = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient).build()

        return this
    }


}