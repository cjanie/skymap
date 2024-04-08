package com.vaonis.skymap.infrastructure

import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.businesslogic.AstronomicalObjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class InMemoryAstronomicalObjectsRepository(private val astronomicalObjects: List<AstronomicalObject>): AstronomicalObjectRepository {
    override fun list(): Flow<List<AstronomicalObject>> {
        return flowOf(this.astronomicalObjects)
    }
}