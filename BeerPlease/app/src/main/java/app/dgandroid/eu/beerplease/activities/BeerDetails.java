package app.dgandroid.eu.beerplease.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import app.dgandroid.eu.beerplease.adapters.AdapterIngredients;
import app.dgandroid.eu.beerplease.model.Beer;
import app.dgandroid.eu.beerplease.R;
import app.dgandroid.eu.beerplease.utils.Constants;
import app.dgandroid.eu.beerplease.utils.Utility;

public class BeerDetails extends AppCompatActivity {

    private Intent intentExtra;
    private Beer beer;
    private TextView tag, description, contributorsName, dateOfBrew;
    private ImageView image;
    private LinearLayout listLayoutContainer;
    private ImageButton backButton;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_beer_details);
        final ActionBar ab =getSupportActionBar();
        ab.hide();

        CollapsingToolbarLayout collapsing_container = (CollapsingToolbarLayout) findViewById(R.id.collapsing_container);

        listLayoutContainer = (LinearLayout) findViewById(R.id.listLayoutContainer);
        backButton          = (ImageButton) findViewById(R.id.backButton);
        tag                 = (TextView) findViewById(R.id.tag);
        description         = (TextView) findViewById(R.id.description);
        contributorsName    = (TextView) findViewById(R.id.contributorsName);
        dateOfBrew          = (TextView) findViewById(R.id.dateOfBrew);
        image               = (ImageView) findViewById(R.id.imgBeer);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fabImage);

        intentExtra = getIntent();
        this.beer = (Beer) intentExtra.getSerializableExtra(Constants.SHARE_BEERS);

        collapsing_container.setTitle(beer.getName());
        tag.setText(beer.getTag());
        description.setText(beer.getDescription());
        contributorsName.setText(beer.getContributorsName());
        dateOfBrew.setText(beer.getDateOfBrew());
        Utility.onGettingImage(this, beer.getImage(), image);

        if(beer.getIngredients().getMalt().size() != 0){
            ListView listView = new ListView(this);
            ImageView imageView = new ImageView(this);
            int resourceId = this.getResources().getIdentifier("malts", "drawable", this.getPackageName());
            Drawable drawable = this.getResources().getDrawable(resourceId);
            imageView.setLayoutParams(new ActionBar.LayoutParams(120,120));
            imageView.setImageDrawable(drawable);
            listLayoutContainer.addView(Utility.createHorizontalLayout("Malts: ",imageView, this));
            listLayoutContainer.addView(listView);
            AdapterIngredients adapter = new AdapterIngredients(this, beer.getIngredients().getMalt());
            listView.setAdapter(adapter);
            listView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            Utility.setListViewHeightBasedOnItems(listView);
        }

        if(beer.getIngredients().getHops().size() != 0){
            ListView listView2 = new ListView(this);
            ImageView imageView = new ImageView(this);
            int resourceId = this.getResources().getIdentifier("hops", "drawable", this.getPackageName());
            Drawable drawable = this.getResources().getDrawable(resourceId);
            imageView.setLayoutParams(new ActionBar.LayoutParams(120,120));
            imageView.setImageDrawable(drawable);
            listLayoutContainer.addView(Utility.createHorizontalLayout("Hops: ",imageView, this));
            listLayoutContainer.addView(listView2);
            AdapterIngredients adapter2 = new AdapterIngredients(this, beer.getIngredients().getHops());
            listView2.setAdapter(adapter2);
            listView2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            Utility.setListViewHeightBasedOnItems(listView2);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BeerDetails.this, FullscreenBeerActivity.class);
                intent.putExtra(Constants.SHARE_IMAGE, beer.getImage());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
        }
    }
}