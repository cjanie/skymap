package com.vaonis.skymap

import android.app.Application
import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.businesslogic.DistanceUnit
import com.vaonis.skymap.businesslogic.GetSkyMapObjectsUseCase
import com.vaonis.skymap.infrastructure.AstronomicalObjectRepositoryImpl
import com.vaonis.skymap.infrastructure.InMemoryAstronomicalObjectsRepository
import com.vaonis.skymap.ui.viewmodels.SkyViewModelFactory

class Launch: Application() {

    // Dependencies injection
    private val astronomicalObjectRepository by lazy {
        AstronomicalObjectRepositoryImpl()
    }

    private val getSkyMapObjectsUseCase by lazy {
        GetSkyMapObjectsUseCase(this.astronomicalObjectRepository)
    }

    val skyViewModelFactory by lazy {
        SkyViewModelFactory(this.getSkyMapObjectsUseCase)
    }

}