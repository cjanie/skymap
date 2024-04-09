package com.vaonis.skymap.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.businesslogic.GetSkyMapObjectsUseCase

class SkyViewModel(getSkyMapObjectsUseCase: GetSkyMapObjectsUseCase): ViewModel() {

    private val _astronomicalObjects: LiveData<List<AstronomicalObject>> = getSkyMapObjectsUseCase().asLiveData()

    val astronomicalObjects: LiveData<List<AstronomicalObject>> = this._astronomicalObjects

}