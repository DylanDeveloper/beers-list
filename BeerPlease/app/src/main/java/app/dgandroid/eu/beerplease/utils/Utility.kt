package app.dgandroid.eu.beerplease.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

/**
 * Created by Duilio on 22/05/2017.
 */

object Utility {
    fun onGettingImage(context: Context, imageUrl: String, imageView: ImageView) {
        Picasso.with(context)
                .load(imageUrl).networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, object : Callback {
                    override fun onSuccess() {}
                    override fun onError() { //Try again online if cache failed
                        val resourceId = context.resources.getIdentifier("default_beer", "drawable", context.packageName)
                        val drawable = context.resources.getDrawable(resourceId)
                        Picasso.with(context)
                                .load(imageUrl)
                                .error(drawable)
                                .into(imageView, object : Callback {
                                    override fun onSuccess() {}
                                    override fun onError() {
                                        Logger.e("Picasso Could not fetch image")
                                    }
                                })
                    }
                })
    }
    fun setIconFromURL(urlImage: String, imageView: ImageView, activity: Activity) {
        Picasso.with(activity)
                .load(urlImage)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        scheduleStartPostponedTransition(imageView, activity)
                    }
                    override fun onError() {}
                })
    }
    fun scheduleStartPostponedTransition(sharedElement: View, activity: Activity) {
        sharedElement.viewTreeObserver.addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        sharedElement.viewTreeObserver.removeOnPreDrawListener(this)
                        activity.startPostponedEnterTransition()
                        return true
                    }
                }
        )
    }
}