package app.dgandroid.eu.beerplease.customs

/**
 * Created by Duilio on 23/05/2017.
 */

import android.support.v7.widget.RecyclerView

abstract class OnVerticalScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        if (!recyclerView!!.canScrollVertically(-1)) {
            onScrolledToTop()
        } else if (!recyclerView.canScrollVertically(1)) {
            onScrolledToBottom()
        }
        if (dy < 0) {
            onScrolledUp(dy)
        } else if (dy > 0) {
            onScrolledDown(dy)
        }
    }

    open fun onScrolledUp(dy: Int) {
        onScrolledUp()
    }

    open fun onScrolledDown(dy: Int) {
        onScrolledDown()
    }

    fun onScrolledUp() {}

    fun onScrolledDown() {}

    fun onScrolledToTop() {}

    open fun onScrolledToBottom() {}
}
