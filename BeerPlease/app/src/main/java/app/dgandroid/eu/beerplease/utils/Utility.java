package app.dgandroid.eu.beerplease.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by Duilio on 22/05/2017.
 */

public class Utility {

    public static void onGettingImage(final Context context, final String imageUrl, final ImageView imageView){
        Picasso.with(context)
                .load(imageUrl).networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                    }
                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        int resourceId = context.getResources().getIdentifier("default_beer", "drawable", context.getPackageName());
                        Drawable drawable = context.getResources().getDrawable(resourceId);
                        Picasso.with(context)
                                .load(imageUrl)
                                .error(drawable)
                                .into(imageView, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }
                                    @Override
                                    public void onError() {
                                        Logger.e("Picasso Could not fetch image");
                                    }
                                });
                    }
                });
    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {
            int numberOfItems = listAdapter.getCount();
            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }
            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + 125;
            listView.setLayoutParams(params);
            listView.requestLayout();
            listView.setDivider(null);
            return true;
        } else {
            return false;
        }
    }

    public static void addHeaderView(ListView listView, String title, Context context) {
        TextView textView = new TextView(context);
        textView.setText(title);
        addPaddingTopAndBottom(textView, context, 8);
        //textView.setTypeface(null, Typeface.BOLD);
        textView.setTextSize(14);
        listView.addHeaderView(textView);
    }

    public static void addPaddingTopAndBottom(View view, Context context, int padding){
        float density = context.getResources().getDisplayMetrics().density;
        int paddingDp = (int)(padding * density);
        view.setPadding(0,paddingDp,0,paddingDp);
    }
}