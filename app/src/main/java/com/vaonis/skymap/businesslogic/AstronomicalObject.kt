package com.vaonis.skymap.businesslogic

data class AstronomicalObject(
    val id: String,
    val ra: Float, val de: Float,
    val category: String,
    val distance: Long,
    val distanceUnit: DistanceUnit
)