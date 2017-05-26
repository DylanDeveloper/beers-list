package app.dgandroid.eu.beerplease.rest;

import android.content.Context;
import java.util.List;
import app.dgandroid.eu.beerplease.controllers.Manager;
import app.dgandroid.eu.beerplease.customs.ProgressLoading;
import app.dgandroid.eu.beerplease.model.Beer;
import app.dgandroid.eu.beerplease.utils.Config;
import app.dgandroid.eu.beerplease.utils.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Duilio on 23/05/2017.
 */

public class ActionCall {

    private int page;
    private ProgressLoading loading;
    private Call<List<Beer>> call;
    private ActionDelegate delegate;

    public ActionCall (Context context, ActionDelegate delegate) {
        this.delegate = delegate;
        loading = new ProgressLoading(context);
    }

    public void execute() {
        loading.onShow();
        Client client = new Client();
        page = Manager.getInstance().getPage();
        ApiInterface apiInterface = client.getClient().create(ApiInterface.class);
        call = apiInterface.getBeers(page, Config.PER_PAGE);
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                int statusCode = response.code();
                if(statusCode == 200) {
                    delegate.onSuccess(response);
                    Manager.getInstance().incrementPage();
                } else {
                    Logger.e("NOTHING statusCode = " + statusCode);
                    delegate.onFailure("On Failure - " + statusCode);
                }
                loading.dismiss();
            }
            @Override
            public void onFailure(Call<List<Beer>>call, Throwable t) {
                Logger.e("Throwable t = " + t.getMessage());
                delegate.onFailure(t);
                loading.dismiss();
            }
        });
    }
}