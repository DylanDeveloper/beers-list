package app.dgandroid.eu.beerplease.adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.support.v4.util.Pair
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.dgandroid.eu.beerplease.R
import app.dgandroid.eu.beerplease.activities.BeerDetails
import app.dgandroid.eu.beerplease.model.Beer
import app.dgandroid.eu.beerplease.utils.Constants
import app.dgandroid.eu.beerplease.utils.Utility
import kotlinx.android.synthetic.main.beer_item.view.*

/**
 * Created by Duilio on 22/05/2017.
 */

class BeerAdapter(private val beerList: MutableList<Beer>, private val activity: Activity) : RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.beer_item, parent, false)
        return BeerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bindBeer(beerList[position], activity)
    }

    override fun getItemCount(): Int {
        return beerList.size
    }

    class BeerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindBeer(beer : Beer, activity: Activity) {
            itemView.tagBeer.text   = beer.name
            itemView.nameBeer.text  = beer.name
            Utility.setIconFromURL(beer.image, itemView.beerImage, activity)
            itemView.beerImage.transitionName =  "${activity.getString(R.string.transition_string)} ${beer.id}"
            itemView.setOnClickListener {
                val intent = Intent(activity, BeerDetails::class.java)
                intent.putExtra(Constants.SHARE_BEERS, beer)
                val pair1 = Pair.create(itemView.beerImage, itemView.beerImage.transitionName)
                val activityOptions = ActivityOptions
                        .makeSceneTransitionAnimation(activity, pair1.first, pair1.second)
                activity.startActivity(intent, activityOptions.toBundle())
            }
        }
    }
}