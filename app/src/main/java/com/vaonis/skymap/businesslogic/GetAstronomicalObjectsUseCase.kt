package com.vaonis.skymap.businesslogic

class GetAstronomicalObjectsUseCase(private val astronomicalObjectRepository: AstronomicalObjectRepository) {

    operator fun invoke() = handle()

    private fun handle(): List<AstronomicalObject> {
        return this.astronomicalObjectRepository.list()
    }
}