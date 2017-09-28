package app.dgandroid.eu.beerplease.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import app.dgandroid.eu.beerplease.R
import app.dgandroid.eu.beerplease.utils.Constants
import app.dgandroid.eu.beerplease.utils.Utility
import kotlinx.android.synthetic.main.activity_fullscreenbeer.*

class FullscreenBeerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.enterTransition  = Explode()
        window.exitTransition   = Explode()
        setContentView(R.layout.activity_fullscreenbeer)
        supportActionBar?.hide()
        val urlImage = intent.getStringExtra(Constants.SHARE_IMAGE)
        Utility.onGettingImage(this, urlImage, touchView)
        closeBTN.setOnClickListener { finish() }
    }
}