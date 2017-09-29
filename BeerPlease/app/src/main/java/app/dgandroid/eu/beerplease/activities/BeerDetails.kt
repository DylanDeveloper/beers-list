package app.dgandroid.eu.beerplease.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import app.dgandroid.eu.beerplease.model.Beer
import app.dgandroid.eu.beerplease.R
import app.dgandroid.eu.beerplease.adapters.IngredientsAdapter
import app.dgandroid.eu.beerplease.model.Ingredients
import app.dgandroid.eu.beerplease.utils.Constants
import app.dgandroid.eu.beerplease.utils.Utility
import kotlinx.android.synthetic.main.activity_beer_details.*
import java.util.HashMap

class BeerDetails : AppCompatActivity() {

    private var beer            = Beer()
    private val mListDataChild  = HashMap<String, List<Ingredients.IngredientType>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        window.enterTransition  = Explode()
        window.exitTransition   = Explode()
        setContentView(R.layout.activity_beer_details)
        supportActionBar?.hide()
        beer = intent.getSerializableExtra(Constants.SHARE_BEERS) as Beer

        collapsing_container.title  = beer.name
        tag.text                    = beer.tag
        description.text            = beer.description
        contributorsName.text       = beer.contributorsName
        dateOfBrew.text             = beer.dateOfBrew

        Utility.onGettingImage(this, beer.image, imgBeer)

        if (beer.ingredients.malt.size != 0) {
            mListDataChild.put("Malts", beer.ingredients.malt)
        }
        if (beer.ingredients.hops.size != 0) {
            mListDataChild.put("Hops", beer.ingredients.hops)
        }

        val listDataHeader      = ArrayList<String>(mListDataChild.keys)
        val ingredientAdapter   = IngredientsAdapter(this, listDataHeader, mListDataChild)
        expListView.setAdapter(ingredientAdapter)
        expListView.setGroupIndicator(null)

        backButton.setOnClickListener {finish()}
        fabImage.setOnClickListener {
            val intent = Intent(this@BeerDetails, FullscreenBeerActivity::class.java)
            intent.putExtra(Constants.SHARE_IMAGE, beer.image)
            startActivity(intent)
        }
    }
}