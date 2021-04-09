package com.chalitta.myanimelist.remote

import com.chalitta.myanimelist.repository.Constant
import com.google.gson.GsonBuilder
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    fun getRetroInstance(url: String): ServerApi {

        val certPinner = CertificatePinner.Builder()
                .add(url, "sha256/" + Constant().SSL_SHA256_1)
                .build()
        val okHttpClient = OkHttpClient.Builder()
                .certificatePinner(certPinner)
                .build()

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()

        return retrofit.create<ServerApi>(ServerApi::class.java)
    }
}