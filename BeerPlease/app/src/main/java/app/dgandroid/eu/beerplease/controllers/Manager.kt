package app.dgandroid.eu.beerplease.controllers

/**
 * Created by Duilio on 23/05/2017.
 */

object Manager {
    var page = 1
        private set

    fun incrementPage() {
        this.page++
    }

    fun clear() {
        this.page = 1
    }
}