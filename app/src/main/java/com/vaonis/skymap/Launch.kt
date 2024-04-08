package com.vaonis.skymap

import android.app.Application
import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.businesslogic.DistanceUnit
import com.vaonis.skymap.businesslogic.GetAstronomicalObjectsUseCase
import com.vaonis.skymap.infrastructure.InMemoryAstronomicalObjectsRepository
import com.vaonis.skymap.ui.viewmodels.SkyViewModelFactory

class Launch: Application() {

    // Fake data
    val fakeData = arrayListOf(
        AstronomicalObject("id 1", 10F, 10F, "galaxy-star", 12F, DistanceUnit.LIGHT_YEARS),
        AstronomicalObject("id 2", 20F, 30F, "galaxy-planet", 20F, DistanceUnit.LIGHT_YEARS)
    )

    // Dependencies injection

    val astronomicalObjectRepository by lazy {
        InMemoryAstronomicalObjectsRepository(this.fakeData)
    }

    val getAstronomicalObjectsUseCase by lazy {
        GetAstronomicalObjectsUseCase(this.astronomicalObjectRepository)
    }

    val skyViewModelFactory by lazy {
        SkyViewModelFactory(this.getAstronomicalObjectsUseCase)
    }

}