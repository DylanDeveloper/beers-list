package app.dgandroid.eu.beerplease.activities;

import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.List;
import app.dgandroid.eu.beerplease.adapters.BeerAdapter;
import app.dgandroid.eu.beerplease.controllers.Manager;
import app.dgandroid.eu.beerplease.customs.OnVerticalScrollWithPagingSlopListener;
import app.dgandroid.eu.beerplease.model.Beer;
import app.dgandroid.eu.beerplease.R;
import app.dgandroid.eu.beerplease.rest.ActionCall;
import app.dgandroid.eu.beerplease.rest.ActionDelegate;
import app.dgandroid.eu.beerplease.utils.Logger;
import retrofit2.Response;

public class BeersListActivity extends AppCompatActivity implements ActionDelegate {

    private RecyclerView recyclerView;
    private List<Beer> beers;
    private BeerAdapter adapter;
    private ActionCall actionCall;
    private ImageButton refreshBTN;
    private Parcelable recyclerViewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers_list);
        ActionBar ab =getSupportActionBar();
        ab.setElevation(0);
        getWindow().setExitTransition(new Explode());

        recyclerView    = (RecyclerView) findViewById(R.id.beerListView);
        refreshBTN      = (ImageButton) findViewById(R.id.refreshBTN);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        actionCall = new ActionCall(this, this);
        actionCall.execute();

        recyclerView.addOnScrollListener(new OnVerticalScrollWithPagingSlopListener(this) {
            @Override
            public void onScrolledToBottom() {
                super.onScrolledToBottom();
                actionCall.execute();
            }
        });

        refreshBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshBTN.setVisibility(View.GONE);
                actionCall.execute();
            }
        });
    }

    @Override
    public void onSuccess(Response<List<Beer>> response) {
        if(beers == null) {
            beers = response.body();
        } else {
            beers.addAll(response.body());
        }
        Logger.i("beers = " + beers.size());
        recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
        adapter = new BeerAdapter(beers, R.layout.beer_item, this);
        recyclerView.setAdapter(adapter);
        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Object t) {
        Toast.makeText(this, t.toString(), Toast.LENGTH_LONG).show();
        refreshBTN.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(beers != null) {
            beers.clear();
            Manager.getInstance().clear();
        }
        finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
        }
    }
}