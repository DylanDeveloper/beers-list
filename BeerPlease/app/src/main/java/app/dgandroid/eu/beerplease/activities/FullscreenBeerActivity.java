package app.dgandroid.eu.beerplease.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import app.dgandroid.eu.beerplease.customs.TouchImageView;
import app.dgandroid.eu.beerplease.R;
import app.dgandroid.eu.beerplease.utils.Constants;
import app.dgandroid.eu.beerplease.utils.Utility;

public class FullscreenBeerActivity extends AppCompatActivity {

    private Intent intentExtra;
    private TouchImageView touchImageView;
    private ImageButton closeBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreenbeer);
        getSupportActionBar().hide();

        touchImageView  = (TouchImageView) findViewById(R.id.imageView);
        closeBTN        = (ImageButton) findViewById(R.id.closeBTN);

        intentExtra = getIntent();
        String urlImage = intentExtra.getStringExtra(Constants.SHARE_IMAGE);
        Utility.onGettingImage(this, urlImage, touchImageView);

        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}