package com.vaonis.skymap.businesslogic

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetSkyMapObjectsUseCase(private val astronomicalObjectRepository: AstronomicalObjectRepository) {

    operator fun invoke() = handle()

    private fun handle(): Flow<List<AstronomicalObject>> {
        return this.filterObjectsToDisplayInSkyMap(this.astronomicalObjectRepository.list())
    }

    private fun filterObjectsToDisplayInSkyMap(astronomicalObjects :Flow<List<AstronomicalObject>>): Flow<List<AstronomicalObject>> {
        return astronomicalObjects
            .map { astronomicalObjects ->
                astronomicalObjects.filter { astronomicalObject ->
                    astronomicalObject.de >= 0 }
            }
            .flowOn(Dispatchers.IO)
    }
}