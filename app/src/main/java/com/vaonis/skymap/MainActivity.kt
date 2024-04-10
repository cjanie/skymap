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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
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
                                var columnHeightInPx by remember {
                                    mutableStateOf(0f)
                                }
                                var columnWidthInPx by remember {
                                    mutableStateOf(0f)
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .onGloballyPositioned { coordinates ->
                                            // Set column height using the LayoutCoordinates
                                            columnHeightInPx = coordinates.size.height.toFloat()
                                            columnWidthInPx = coordinates.size.width.toFloat()
                                        }
                                ) {
                                    val viewSizeRef = if (columnWidthInPx < columnHeightInPx) columnWidthInPx else columnHeightInPx


                                    setUpSkyMap(astronomicalObjects, viewSizeRef)
                                    println("column size = $viewSizeRef")



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
    private fun setUpSkyMap(astronomicalObjects: List<AstronomicalObject>, viewSizeInPx: Float) {
        for(astronomicalObject in astronomicalObjects) {
            AstronomicalObjectInSkyCanvasComposable(
                astronomicalObject, viewSizeInPx)
        }
    }
}

@Composable
fun Sky() {
    // TODO
}

