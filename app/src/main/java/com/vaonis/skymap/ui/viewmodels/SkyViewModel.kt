package com.vaonis.skymap.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.businesslogic.GetAstronomicalObjectsUseCase

class SkyViewModel(getAstronomicalObjectsUseCase: GetAstronomicalObjectsUseCase): ViewModel() {

    private val _astronomicalObjects: LiveData<List<AstronomicalObject>> = getAstronomicalObjectsUseCase().asLiveData()

    val astronomicalObjects: LiveData<List<AstronomicalObject>> = this._astronomicalObjects

}