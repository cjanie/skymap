package com.vaonis.skymap.infrastructure

import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.businesslogic.AstronomicalObjectRepository
import kotlinx.coroutines.flow.Flow

class AstronomicalObjectRepositoryImpl: AstronomicalObjectRepository {
    override fun list(): Flow<List<AstronomicalObject>> {
        TODO("Not yet implemented")
    }
}