package com.vaonis.skymap.ui.draw

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.vaonis.skymap.businesslogic.AstronomicalObject

// https://www.youtube.com/watch?v=cCIPYW_N12o


const val skyRadiusInPx = 90F

@Composable
fun AstronomicalObjectInSkyCanvasComposable(astronomicalObject: AstronomicalObject, viewSizeInPx: Float) {
    val skyDiameterInPx = skyRadiusInPx * 2
    val scale = viewSizeInPx / skyDiameterInPx

    Canvas(
        modifier = Modifier.fillMaxWidth().scale(scale).rotate(astronomicalObject.ra.toFloat())
    ) {
        //center, size
        val matrixStrokeWidthInPx = 0.1.dp.toPx()
        val circleStrokeWidthInPx = 0.25.dp.toPx()
        val astronomicalObjectRadiusInPx = 0.5.dp.toPx()

        //drawMatrix(this, matrixStrokeWidthInPx)
        drawSky(this, circleStrokeWidthInPx)
        drawAstronomicalObject(this, astronomicalObjectRadiusInPx, astronomicalObject.de.toFloat())
    }
}
private fun drawSky(drawScope: DrawScope, strokeWidthInPx: Float) {
    // Display the center point
    val skyCenterPointRadius = strokeWidthInPx
    drawSkyCenterPoint(drawScope, skyCenterPointRadius)

    // Display the 3 concentric circles
    var index = 1
    do {
        drawCircleStroke(drawScope, index * (skyRadiusInPx / 3F), strokeWidthInPx)
        index++
    } while (index <= 3)
}

private fun drawSkyCenterPoint(drawScope: DrawScope, skyCenterPointRadius: Float) {
    drawScope.drawCircle(Color.White, skyCenterPointRadius)
}

private fun drawAstronomicalObject(drawScope: DrawScope, astronomicalObjectradiusInPx: Float, de: Float) {
    drawScope.drawCircle(Color.White, astronomicalObjectradiusInPx, Offset(drawScope.center.x, drawScope.center.y - (skyRadiusInPx - de)))
}
private fun drawMatrix(drawScope: DrawScope, strokeWidthInPx: Float) {
    var index = 1
    do {
        drawCircleStroke(drawScope, index * (skyRadiusInPx / 9F), strokeWidthInPx, Color.Green)
        index++
    } while (index <= 9)
    drawScope.drawLine(Color.Red, Offset(drawScope.center.x, drawScope.center.y - skyRadiusInPx), Offset(drawScope.center.x, drawScope.center.y + skyRadiusInPx), strokeWidthInPx)
    drawScope.drawLine(Color.Cyan, Offset(drawScope.center.x - skyRadiusInPx, drawScope.center.y), Offset(drawScope.center.x + skyRadiusInPx, drawScope.center.y), strokeWidthInPx)
}
private fun drawCircleStroke(drawScope: DrawScope, radiusInPx: Float, strokeWidthInPx: Float, color: Color = Color.White) {
    drawScope.drawCircle(
        color = color,
        radius = radiusInPx,
        style = Stroke(
            width = strokeWidthInPx
        )
    )
}
