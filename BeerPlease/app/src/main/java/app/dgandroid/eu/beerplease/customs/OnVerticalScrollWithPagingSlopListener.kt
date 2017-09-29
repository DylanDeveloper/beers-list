package app.dgandroid.eu.beerplease.customs

/**
 * Created by Duilio on 23/05/2017.
 */

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewConfiguration

abstract class OnVerticalScrollWithPagingSlopListener(context: Context) : OnVerticalScrollListener() {

    private val mPagingTouchSlop: Int = ViewConfiguration.get(context).scaledPagingTouchSlop

    // Distance in y since last idle or direction change.
    private var mDy: Int = 0

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            mDy = 0
        }
    }

    override fun onScrolledUp(dy: Int) {
        mDy = if (mDy < 0) mDy + dy else dy
        if (mDy < -mPagingTouchSlop) {
            onScrolledUp()
        }
    }

    override fun onScrolledDown(dy: Int) {
        mDy = if (mDy > 0) mDy + dy else dy
        if (mDy > mPagingTouchSlop) {
            onScrolledDown()
        }
    }
}