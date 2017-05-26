package app.dgandroid.eu.beerplease.controllers;

/**
 * Created by Duilio on 23/05/2017.
 */

public class Manager {

    private static Manager instance;
    private int page = 1;

    public static Manager getInstance(){
        if(instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    public void incrementPage(){
        this.page++;
    }

    public void clear(){
        this.page = 1;
    }

    public int getPage(){
        return this.page;
    }
}