package net.scootergo.app.model


data class ScooterResponse(
    var products: List<Scooter>? = null,
    var exception: Exception? = null
)