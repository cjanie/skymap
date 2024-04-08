package com.vaonis.skymap.infrastructure

import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.businesslogic.AstronomicalObjectRepository

class InMemoryAstronomicalObjectsRepository(private val astronomicalObjects: List<AstronomicalObject>): AstronomicalObjectRepository {
    override fun list(): List<AstronomicalObject> {
        return this.astronomicalObjects
    }
}