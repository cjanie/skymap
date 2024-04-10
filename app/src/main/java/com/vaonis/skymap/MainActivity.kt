package com.vaonis.skymap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.businesslogic.DistanceUnit
import com.vaonis.skymap.ui.componants.Header
import com.vaonis.skymap.ui.draw.AstronomicalObjectInSkyCanvasComposable
import com.vaonis.skymap.ui.theme.SkyMapTheme
import com.vaonis.skymap.ui.viewmodels.SkyViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : ComponentActivity() {

    private val skyViewModel: SkyViewModel by viewModels {
        (this.application as Launch).skyViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val astronomicalObjectsObserver: Observer<List<AstronomicalObject>> = Observer { astronomicalObjects ->
            // View
            setContent {
                SkyMapTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.Black
                    ) {
                        Column() {
                            Row(Modifier.padding(20.dp)) {
                                Header("SkyMap")
                            }

                            Row(
                                Modifier.padding(20.dp)) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {

                                    setUpSkyMap(astronomicalObjects)


                                }

                            }
                        }
                    }
                }
            }


            println(astronomicalObjects.size)
        }
        this.skyViewModel.astronomicalObjects.observe(this, astronomicalObjectsObserver)

    }
    
    @Composable
    private fun setUpSkyMap(astronomicalObjects: List<AstronomicalObject>) {
        for(astronomicalObject in astronomicalObjects) {
            AstronomicalObjectInSkyCanvasComposable(
                astronomicalObject)
        }
    }
}

@Composable
fun Sky() {
    // TODO
}

