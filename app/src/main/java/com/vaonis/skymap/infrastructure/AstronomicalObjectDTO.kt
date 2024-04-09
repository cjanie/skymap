package com.vaonis.skymap.infrastructure

import kotlinx.serialization.Serializable

@Serializable
data class AstronomicalObjectDTO(
    val id: String,
    val ra: Double = -1.0,
    val de: Double = -1.0,
    val category: String,
    val distance: Double = -1.0,
    val distanceUnit: String
)
// Default value for 'ra', 'de' and 'distance' to avoid kotlinx.serialization.MissingFieldException: Field 'X' is required, but it was missing
// See https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/basic-serialization.md#optional-properties

/*
JSON object structure:
{
        "id": "51Peg",
        "constellation": "Peg",
        "category": "star-single",
        "appVersion": "1.9",
        "ra": 344.3665,
        "de": 20.7688,
        "duration": 5,
        "grade": 4,
        "type": "OP",
        "magnitude": 5.46,
        "sizeUnit": "N/A",
        "realSize": 1570000,
        "realSizeUnit": "km",
        "distance": 49,
        "discoveredBy": "N/A",
        "discoveredIn": "N/A",
        "distanceUnit": "ly"
    },
 */