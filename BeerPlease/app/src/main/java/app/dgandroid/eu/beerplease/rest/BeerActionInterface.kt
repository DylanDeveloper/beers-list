package app.dgandroid.eu.beerplease.rest

import app.dgandroid.eu.beerplease.model.Beer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Duilio on 22/05/2017.
 */

interface BeerActionInterface {
    @GET("beers?")
    fun getBeers(@Query("page") page: Int, @Query("per_page") per_page: Int): Call<MutableList<Beer>>
}
