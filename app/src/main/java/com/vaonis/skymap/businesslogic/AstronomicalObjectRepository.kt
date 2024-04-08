package com.vaonis.skymap.businesslogic

interface AstronomicalObjectRepository {

    fun list(): List<AstronomicalObject>
}