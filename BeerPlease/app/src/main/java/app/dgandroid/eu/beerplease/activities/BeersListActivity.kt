package app.dgandroid.eu.beerplease.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.transition.Explode
import android.view.View
import app.dgandroid.eu.beerplease.adapters.BeerAdapter
import app.dgandroid.eu.beerplease.customs.OnVerticalScrollWithPagingSlopListener
import app.dgandroid.eu.beerplease.model.Beer
import app.dgandroid.eu.beerplease.R
import app.dgandroid.eu.beerplease.controllers.Manager
import app.dgandroid.eu.beerplease.rest.ActionCall
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_beers_list.*
import org.jetbrains.anko.longToast

class BeersListActivity : AppCompatActivity() {

    private var beers = mutableListOf<Beer>()
    private var adapter: BeerAdapter?           = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beers_list)
        window.exitTransition = Explode()

        beerListView.layoutManager = LinearLayoutManager(this)

        val actionCall = ActionCall(this, object : ActionCall.ActionDelegate {
            override fun onSuccess(response: Response<MutableList<Beer>>) {
                when (beers) {
                    null -> beers = response.body()
                    else -> beers.addAll(response.body())
                }
                val recyclerViewState = beerListView.layoutManager.onSaveInstanceState()
                adapter = BeerAdapter(beers, this@BeersListActivity)
                beerListView.adapter = adapter
                beerListView.layoutManager.onRestoreInstanceState(recyclerViewState)
                adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(t: Any) {
                longToast(t.toString())
                refreshBTN.visibility = View.VISIBLE
            }
        })
        actionCall.execute()

        beerListView.addOnScrollListener(object : OnVerticalScrollWithPagingSlopListener(this) {
            override fun onScrolledToBottom() {
                actionCall.execute()
            }
        })

        refreshBTN.setOnClickListener {
            refreshBTN.visibility = View.GONE
            actionCall.execute()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Manager.clear()
        beers.clear()
    }
}