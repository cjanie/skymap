package com.vaonis.skymap.infrastructure

import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.businesslogic.AstronomicalObjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AstronomicalObjectRepositoryImpl: AstronomicalObjectRepository {

    companion object {
        val thread = Thread {
            try {
                // Your code goes here
                val httpClient = OkHttpClient()
                val request: Request = Request.Builder()
                    .url("https://vaonis-back-prod-eu.herokuapp.com/catalog/")
                    .header("Authorization", "Token 060a0d44f1e4fa13ff7d6e849ed6afcccce298fd28f42d82870275f8c407b7f7")
                    .build()

                try {
                    val response: Response = httpClient.newCall(request).execute()
                    println(response.body?.string())
                } catch (e: IOException) {
                    println(e)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    override fun list(): Flow<List<AstronomicalObject>> {
        thread.start()
        return flowOf(ArrayList()) //TODO return deserialized list
    }
}