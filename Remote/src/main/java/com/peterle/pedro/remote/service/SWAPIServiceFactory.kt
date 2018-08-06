package com.peterle.pedro.remote.service

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object SWAPIServiceFactory {

    fun makeSWAPIService(isDebug: Boolean): SWAPIService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor((isDebug)))
        return makeSWAPIService(okHttpClient, Gson())
    }

    private fun makeSWAPIService(okHttpClient: OkHttpClient, gson: Gson): SWAPIService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(SWAPIService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                /*.addInterceptor {chain ->
                    val original = chain.request()
                    val originalHttpUrl = original.url()

                    val url = originalHttpUrl.newBuilder()
                            .addQueryParameter("APPID", "03f70bec9a53b19afb1ed17e5e0cab9c")
                            .build()

                    chain.proceed(original.newBuilder().url(url).build())
                }*/
                .readTimeout(Int.MAX_VALUE.toLong(), TimeUnit.MILLISECONDS)
                .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

}