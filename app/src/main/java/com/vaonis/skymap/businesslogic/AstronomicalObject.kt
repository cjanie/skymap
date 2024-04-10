package com.vaonis.skymap.businesslogic

data class AstronomicalObject(
    val id: String,
    val ra: Double, val de: Double,
    val category: String,
    val distance: Double?,
    val distanceUnit: DistanceUnit
)