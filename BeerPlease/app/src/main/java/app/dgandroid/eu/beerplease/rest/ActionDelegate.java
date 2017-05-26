package app.dgandroid.eu.beerplease.rest;

import java.util.List;
import app.dgandroid.eu.beerplease.model.Beer;
import retrofit2.Response;

/**
 * Created by Duilio on 23/05/2017.
 */

public interface ActionDelegate {
    void onSuccess(Response<List<Beer>> response);
    void onFailure(Object t);
}
