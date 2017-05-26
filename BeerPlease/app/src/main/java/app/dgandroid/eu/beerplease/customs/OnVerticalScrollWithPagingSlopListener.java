package app.dgandroid.eu.beerplease.customs;

/**
 * Created by Duilio on 23/05/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewConfiguration;

public abstract class OnVerticalScrollWithPagingSlopListener extends OnVerticalScrollListener {

    private final int mPagingTouchSlop;

    // Distance in y since last idle or direction change.
    private int mDy;

    public OnVerticalScrollWithPagingSlopListener(Context context) {
        mPagingTouchSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();
    }

    @Override
    public final void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            mDy = 0;
        }
    }

    @Override
    public final void onScrolledUp(int dy) {
        mDy = mDy < 0 ? mDy + dy : dy;
        if (mDy < -mPagingTouchSlop) {
            onScrolledUp();
        }
    }

    @Override
    public final void onScrolledDown(int dy) {
        mDy = mDy > 0 ? mDy + dy : dy;
        if (mDy > mPagingTouchSlop) {
            onScrolledDown();
        }
    }
}