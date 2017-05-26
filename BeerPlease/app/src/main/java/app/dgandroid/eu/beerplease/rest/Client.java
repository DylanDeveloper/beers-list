package app.dgandroid.eu.beerplease.rest;

import app.dgandroid.eu.beerplease.utils.Config;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Duilio on 22/05/2017.
 */

public class Client {

    private Retrofit retrofit = null;

    public Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}