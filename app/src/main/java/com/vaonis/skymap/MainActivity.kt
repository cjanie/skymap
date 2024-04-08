package com.vaonis.skymap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.vaonis.skymap.businesslogic.AstronomicalObject
import com.vaonis.skymap.ui.theme.SkyMapTheme
import com.vaonis.skymap.ui.viewmodels.SkyViewModel

class MainActivity : ComponentActivity() {

    private val skyViewModel: SkyViewModel by viewModels {
        (this.application as Launch).skyViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View
        setContent {
            SkyMapTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

        val astronomicalObjectsObserver: Observer<List<AstronomicalObject>> = Observer { astronomicalObjects ->
            this.setUpSkyMap(astronomicalObjects)
        }
        this.skyViewModel.astronomicalObjects.observe(this, astronomicalObjectsObserver)

        this.skyViewModel.fetchData()
    }

    private fun setUpSkyMap(astronomicalObjects: List<AstronomicalObject>) {
        // TODO
        println(astronomicalObjects.size)
    }
}

@Composable
fun Sky() {
    // TODO
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SkyMapTheme {
        Greeting("Android")
    }
}