package com.vaonis.skymap.infrastructure

import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.businesslogic.AstronomicalObjectRepository
import com.vaonis.skymap.businesslogic.DistanceUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AstronomicalObjectRepositoryImpl: AstronomicalObjectRepository {

    private val astronomicalObjects: Flow<List<AstronomicalObject>> = flow {
        try {
            val httpClient = OkHttpClient()
            val request: Request = Request.Builder()
                .url(API_URL)
                .header(HEADER_AUTH_KEY, HEADER_AUTH_VALUE)
                .build()

            try {
                val response: Response = httpClient.newCall(request).execute()
                val jsonResult: String? = response.body?.string()
                val astronomicalObjectsDTO = Json { ignoreUnknownKeys = true }.decodeFromString<List<AstronomicalObjectDTO>>(jsonResult!!)
                println(astronomicalObjectsDTO.toString())

                val formatedAstronomicalObjects = ArrayList<AstronomicalObject>()
                for(dto in astronomicalObjectsDTO) {
                    val astronomicalObject = convertDtoToAstronomicalObject(dto)
                    if (astronomicalObject != null) {
                        formatedAstronomicalObjects.add(astronomicalObject)
                    }
                }

                emit(formatedAstronomicalObjects)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    override fun list(): Flow<List<AstronomicalObject>> {
        return astronomicalObjects
    }

    private fun convertDtoToAstronomicalObject(dto: AstronomicalObjectDTO): AstronomicalObject? {
        if(dto.ra == null || dto.de == null) return null
        else return AstronomicalObject(
            dto.id,
            dto.ra, dto.de,
            dto.category,
            dto.distance,
            distanceUnit = if (dto.distanceUnit == "ly") DistanceUnit.LIGHT_YEARS else DistanceUnit.NOT_APPLICABLE )
    }

}