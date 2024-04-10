package com.vaonis.skymap

import android.app.Application
import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.businesslogic.DistanceUnit
import com.vaonis.skymap.businesslogic.GetSkyMapObjectsUseCase
import com.vaonis.skymap.infrastructure.AstronomicalObjectRepositoryImpl
import com.vaonis.skymap.infrastructure.InMemoryAstronomicalObjectsRepository
import com.vaonis.skymap.ui.viewmodels.SkyViewModelFactory

class Launch: Application() {

    // For test
    val astronomicalObjetcs = listOf(
        AstronomicalObject("Regulus", 152.0914, 11.9675, "star-multiple-star", 79.0, DistanceUnit.LIGHT_YEARS)
    )
    // Dependencies injection
    private val astronomicalObjectRepository by lazy {
        AstronomicalObjectRepositoryImpl()
        //InMemoryAstronomicalObjectsRepository(astronomicalObjetcs)
    }

    private val getSkyMapObjectsUseCase by lazy {
        GetSkyMapObjectsUseCase(this.astronomicalObjectRepository)
    }

    val skyViewModelFactory by lazy {
        SkyViewModelFactory(this.getSkyMapObjectsUseCase)
    }

}