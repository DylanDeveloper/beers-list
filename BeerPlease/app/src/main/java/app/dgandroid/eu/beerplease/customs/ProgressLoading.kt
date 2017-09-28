package app.dgandroid.eu.beerplease.customs

import android.app.ProgressDialog
import android.content.Context

/**
 * Created by Duilio on 23/05/2017.
 */

class ProgressLoading(context: Context) {

    private val progressDialog: ProgressDialog = ProgressDialog(context)

    fun onShow() {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    fun dismiss() {
        progressDialog.dismiss()
    }
}