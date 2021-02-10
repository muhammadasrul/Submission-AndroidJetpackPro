package com.asrul.jffplus.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val resource = "RESOURCE"
    private val espressoIdlingResource = CountingIdlingResource(resource)

    fun increment() {
        espressoIdlingResource.increment()
    }

    fun decrement() {
        espressoIdlingResource.decrement()
    }

    fun getEspressoIdlingResource(): IdlingResource = espressoIdlingResource
}