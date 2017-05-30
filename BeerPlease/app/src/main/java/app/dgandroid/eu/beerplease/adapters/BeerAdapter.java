package app.dgandroid.eu.beerplease.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;
import app.dgandroid.eu.beerplease.R;
import app.dgandroid.eu.beerplease.activities.BeerDetails;
import app.dgandroid.eu.beerplease.model.Beer;
import app.dgandroid.eu.beerplease.utils.Constants;

/**
 * Created by Duilio on 22/05/2017.
 */

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {

    private List<Beer> beerList;
    private int rowLayout;
    private Context context;
    private Activity activity;

    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new BeerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BeerViewHolder holder, final int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BeerDetails.class);
                Beer beer = beerList.get(position);
                intent.putExtra(Constants.SHARE_BEERS, beer);
                Pair<View, String> pair1 = Pair.create((View) holder.icon, holder.icon.getTransitionName());
                ActivityOptions activityOptions = ActivityOptions
                            .makeSceneTransitionAnimation(activity, pair1.first, pair1.second);
                activity.startActivity(intent, activityOptions.toBundle());
            }
        });
    }

    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) { //icon, name and tags.
        holder.name.setText(beerList.get(position).getName());
        holder.tag.setText(beerList.get(position).getName());
        setIconFromURL(beerList.get(position).getImage(), context, holder.icon);
        holder.icon.setTransitionName(context.getString(R.string.transition_string) + position);
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }

    public static class BeerViewHolder extends RecyclerView.ViewHolder {
        private TextView  name;
        private TextView  tag;
        private ImageView icon;

        public BeerViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            tag = (TextView) itemView.findViewById(R.id.tag);
            icon = (ImageView) itemView.findViewById(R.id.beerImage);
        }
    }

    public BeerAdapter(List<Beer> beerList, int rowLayout, Activity activity) {
        this.beerList   = beerList;
        this.rowLayout  = rowLayout;
        this.activity   = activity;
        this.context    = activity.getApplicationContext();
    }

    public void setIconFromURL(final String urlImage, final Context context, final ImageView imageView) {
        Picasso.with(context)
                .load(urlImage)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        scheduleStartPostponedTransition(imageView);
                    }

                    @Override
                    public void onError() {
                    }
                });
    }
    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        activity.startPostponedEnterTransition();
                        return true;
                    }
                });
    }
}