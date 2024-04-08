package com.vaonis.skymap

import android.app.Application
import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.businesslogic.DistanceUnit
import com.vaonis.skymap.businesslogic.GetAstronomicalObjectsUseCase
import com.vaonis.skymap.infrastructure.InMemoryAstronomicalObjectsRepository
import com.vaonis.skymap.ui.viewmodels.SkyViewModelFactory

class Launch: Application() {

    // Fake data
    /*
    * {
        "id": "M85",
        "idMessier": "85",
        "idNgc": "4382",
        "constellation": "Com",
        "category": "galaxy-spiral",
        "ra": 186.35,
        "de": 18.183,
        "duration": 30,
        "grade": 1,
        "type": "ODC",
        "gain": 200,
        "exposure": 10000000,
        "orientation": 0,
        "magnitude": 10.5,
        "size": 7.1,
        "realSize": 99000,
        "distance": 60000000,
        "discoveredBy": "Pierre Méchain",
        "discoveredIn": "1781",
        "triviaKeywords": [
            "messier"
        ],
        "distanceUnit": "ly",
        "realSizeUnit": "ly",
        "histogramEnabled": true,
        "histogramLow": -0.75,
        "histogramMedium": 5,
        "histogramHigh": 0,
        "backgroundEnabled": true,
        "backgroundPolyorder": 4
    },
    * */
    val fakeData = arrayListOf(
        AstronomicalObject("M85", 186.35, 18.183, "galaxy-spiral", 60000000, DistanceUnit.LIGHT_YEARS),
        AstronomicalObject("M86", 186.55, 12.95, "galaxy-spiral", 51860000, DistanceUnit.LIGHT_YEARS)
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