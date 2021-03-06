package app.dgandroid.eu.beerplease.customs

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ExpandableListView

/**
 * Created by Duilio on 28/09/2017.
 */

class NonScrollExpandableListView : ExpandableListView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val heightMeasureSpecCustom = View.MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE shr 2, View.MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, heightMeasureSpecCustom)
        val params = layoutParams
        params.height = measuredHeight
    }
}