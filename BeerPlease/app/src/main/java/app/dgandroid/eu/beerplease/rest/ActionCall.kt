package app.dgandroid.eu.beerplease.rest

import android.content.Context
import app.dgandroid.eu.beerplease.controllers.Manager
import app.dgandroid.eu.beerplease.customs.ProgressLoading
import app.dgandroid.eu.beerplease.model.Beer
import app.dgandroid.eu.beerplease.utils.Config
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Duilio on 23/05/2017.
 */

class ActionCall(var context: Context, var delegate: ActionDelegate) {

    private var page: Int = 0
    private val loading: ProgressLoading
    private var call: Call<MutableList<Beer>>? = null

    init {
        loading = ProgressLoading(context)
    }

    fun execute() {
        loading.onShow()
        page = Manager.page
        val beerActionInterface = Client.client!!.create(BeerActionInterface::class.java)
        call = beerActionInterface.getBeers(page, Config.PER_PAGE)
        call!!.enqueue(object : Callback<MutableList<Beer>> {
            override fun onResponse(call: Call<MutableList<Beer>>, response: Response<MutableList<Beer>>) {
                val statusCode = response.code()
                if (statusCode == 200) {
                    delegate.onSuccess(response)
                    Manager.incrementPage()
                } else {
                    delegate.onFailure("On Failure - " + statusCode)
                }
                loading.dismiss()
            }

            override fun onFailure(call: Call<MutableList<Beer>>, t: Throwable) {
                delegate.onFailure(t)
                loading.dismiss()
            }
        })
    }

    interface ActionDelegate {
        fun onSuccess(response: Response<MutableList<Beer>>)
        fun onFailure(t: Any)
    }
}