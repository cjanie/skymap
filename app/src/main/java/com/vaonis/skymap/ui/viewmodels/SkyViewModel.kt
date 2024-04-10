package com.vaonis.skymap.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.businesslogic.GetSkyMapObjectsUseCase
import kotlinx.coroutines.flow.catch

class SkyViewModel(getSkyMapObjectsUseCase: GetSkyMapObjectsUseCase): ViewModel() {

    private val _astronomicalObjects: LiveData<List<AstronomicalObject>> = getSkyMapObjectsUseCase()
        .catch { e -> _error.value = e.message  }
        .asLiveData()

    private val _error = MutableLiveData<String>()

    val astronomicalObjects: LiveData<List<AstronomicalObject>> = this._astronomicalObjects

    val error: LiveData<String> = this._error

}