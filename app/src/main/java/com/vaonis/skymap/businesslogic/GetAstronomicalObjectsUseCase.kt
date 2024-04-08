package com.vaonis.skymap.businesslogic

import kotlinx.coroutines.flow.Flow

class GetAstronomicalObjectsUseCase(private val astronomicalObjectRepository: AstronomicalObjectRepository) {

    operator fun invoke() = handle()

    private fun handle(): Flow<List<AstronomicalObject>> {
        return this.astronomicalObjectRepository.list()
    }
}