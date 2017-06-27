package app.dgandroid.eu.beerplease.rest;

import java.util.List;
import app.dgandroid.eu.beerplease.model.Beer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Duilio on 22/05/2017.
 */

public interface BeerActionInterface {
    @GET("beers?")
    Call<List<Beer>> getBeers(@Query("page") int page, @Query("per_page") int per_page);
}
