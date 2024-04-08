package com.vaonis.skymap.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaonis.skymap.businesslogic.GetAstronomicalObjectsUseCase

class SkyViewModelFactory(private val getAstronomicalObjectsUseCase: GetAstronomicalObjectsUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SkyViewModel::class.java))
            return SkyViewModel(this.getAstronomicalObjectsUseCase) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}