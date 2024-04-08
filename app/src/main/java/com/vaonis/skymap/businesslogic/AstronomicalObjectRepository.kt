package com.vaonis.skymap.businesslogic

import kotlinx.coroutines.flow.Flow

interface AstronomicalObjectRepository {

    fun list(): Flow<List<AstronomicalObject>>
}