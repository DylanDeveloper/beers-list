package app.dgandroid.eu.beerplease.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
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
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) { //icon, name and tags.
        holder.name.setText(beerList.get(position).getName());
        holder.tag.setText(beerList.get(position).getName());
        setIconFromURL(beerList.get(position).getImage(), context, holder.icon);
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

    public BeerAdapter(List<Beer> beerList, int rowLayout, Context context) {
        this.beerList   = beerList;
        this.rowLayout  = rowLayout;
        this.context    = context;
    }

    public void setIconFromURL(final String urlImage, final Context context, final ImageView imageView) {
        Picasso.with(context)
                .load(urlImage)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.default_beer));
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.default_beer));
                    }
                });
    }
}