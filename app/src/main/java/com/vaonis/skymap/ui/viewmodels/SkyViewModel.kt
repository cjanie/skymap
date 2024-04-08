package com.vaonis.skymap.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.businesslogic.GetAstronomicalObjectsUseCase

class SkyViewModel(private val getAstronomicalObjectsUseCase: GetAstronomicalObjectsUseCase): ViewModel() {

    private val _astronomicalObjects = MutableLiveData<List<AstronomicalObject>>(ArrayList())

    val astronomicalObjects: LiveData<List<AstronomicalObject>> = this._astronomicalObjects

    fun fetchData() {
        this._astronomicalObjects.value = getAstronomicalObjectsUseCase()
    }
}