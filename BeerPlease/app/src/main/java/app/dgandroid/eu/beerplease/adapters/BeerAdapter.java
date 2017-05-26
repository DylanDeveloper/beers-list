package app.dgandroid.eu.beerplease.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import app.dgandroid.eu.beerplease.activities.BeerDetails;
import app.dgandroid.eu.beerplease.customs.FlatItem;
import app.dgandroid.eu.beerplease.model.Beer;
import app.dgandroid.eu.beerplease.utils.Constants;

/**
 * Created by Duilio on 22/05/2017.
 */

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {

    private List<Beer> beerList;
    private int rowLayout;
    private Context context;

    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FlatItem view = (FlatItem)LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
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
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) { //icon, name and tags.
        FlatItem view = (FlatItem) holder.itemView;
        view.setNameBeer(beerList.get(position).getName());
        view.setTagBeer(beerList.get(position).getTag());
        view.setIconFromURL(beerList.get(position).getImage(), context);
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }

    public static class BeerViewHolder extends RecyclerView.ViewHolder {
        public BeerViewHolder(View itemView) {
            super(itemView);
        }
    }

    public BeerAdapter(List<Beer> beerList, int rowLayout, Context context) {
        this.beerList   = beerList;
        this.rowLayout  = rowLayout;
        this.context    = context;
    }
}