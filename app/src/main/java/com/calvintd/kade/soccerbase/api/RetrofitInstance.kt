package com.calvintd.kade.soccerbase.api

import com.calvintd.kade.soccerbase.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.anko.doAsyncResult
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

object RetrofitInstance {
    private const val BASE_URL = BuildConfig.BASE_URL

    private var log = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val clientBuilder = OkHttpClient.Builder()
        .addInterceptor(log)

    private val client = clientBuilder.build()

    private val nullOnEmptyConverterFactory = object : Converter.Factory() {
        fun converterFactory() = this

        override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
            val nextResponseBodyConverter = retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
            return Converter<ResponseBody, Any> {
                if (it.contentLength() != 0L) {
                    nextResponseBodyConverter.convert(it)
                } else {
                    null
                }
            }
        }
    }

    fun getInstance(): RetrofitService {return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(nullOnEmptyConverterFactory.converterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(RetrofitService::class.java)
    }
}