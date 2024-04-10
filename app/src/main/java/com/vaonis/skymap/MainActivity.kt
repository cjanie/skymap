package com.vaonis.skymap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.ui.componants.Header
import com.vaonis.skymap.ui.componants.SkyMap
import com.vaonis.skymap.ui.componants.skyRadiusInPx
import com.vaonis.skymap.ui.theme.SkyMapTheme
import com.vaonis.skymap.ui.viewmodels.SkyViewModel

class MainActivity : ComponentActivity() {

    private val skyViewModel: SkyViewModel by viewModels {
        (this.application as Launch).skyViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SkyMapTheme {

                // Data
                var astronomicalObjects: List<AstronomicalObject> by remember {
                    mutableStateOf(ArrayList())
                }
                val astronomicalObjectsObserver: Observer<List<AstronomicalObject>> = Observer { objects ->
                    astronomicalObjects = objects
                }
                this.skyViewModel.astronomicalObjects.observe(this, astronomicalObjectsObserver)

                // View
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black,
                    contentColor = Color.White

                ) {

                    Column() {
                        Row(Modifier.padding(20.dp)) {
                            Header("SkyMap")
                        }

                        Row(
                            Modifier.padding(20.dp)) {
                            var columnHeightInPx by remember {
                                mutableFloatStateOf(0f)
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
                                val skyViewSizeInPx = if (columnWidthInPx < columnHeightInPx) columnWidthInPx else columnHeightInPx
                                val skyDiameterInPx = skyRadiusInPx * 2
                                // Initial scale fills the available space in the column,
                                // Depends of screen size (phone / tablet / landscape mode when turning the phone)
                                val initialScale = skyViewSizeInPx / skyDiameterInPx

                                // Zoom and Pan functionnality implementation https://www.youtube.com/watch?v=3CjOyoqi_PQ
                                var scale by remember {
                                    mutableFloatStateOf(1F)
                                }
                                var offset by remember {
                                    mutableStateOf(Offset.Zero)
                                }

                                BoxWithConstraints(

                                ) {

                                    val state = rememberTransformableState {zoomChange, panChange, rotationChange ->
                                        scale = (scale * zoomChange).coerceIn(1F, 10F)

                                        val extraWidth = (scale - 1) * constraints.maxWidth
                                        val extraHeight = (scale - 1) * constraints.maxHeight

                                        val maxX = extraWidth / 2
                                        val maxY = extraHeight / 2
                                        offset = Offset(
                                            x = (offset.x + panChange.x).coerceIn(-maxX, maxX),
                                            y = (offset.y + panChange.y).coerceIn(-maxY, maxY)
                                        )
                                    }

                                    Column(
                                        modifier = Modifier
                                            .graphicsLayer {
                                                scaleX = initialScale
                                                scaleY = initialScale
                                                translationX = offset.x
                                                translationY = offset.y
                                            }
                                            .transformable(state)
                                        // End of zoom and pan functionality implementation
                                    ) {

                                        SkyMap(astronomicalObjects = astronomicalObjects, scale = scale)

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }



}

