package app.dgandroid.eu.beerplease.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import app.dgandroid.eu.beerplease.model.Ingredients;
import app.dgandroid.eu.beerplease.R;

/**
 * Created by Duilio on 23/05/2017.
 */

public class AdapterIngredients extends ArrayAdapter<Ingredients.IngredientType> {
    private Context mContext;
    private ArrayList<Ingredients.IngredientType> IngredientType;

    public AdapterIngredients(Context context, ArrayList<Ingredients.IngredientType> IngredientType) {
        super(context, 0, IngredientType);
        this.mContext       = context;
        this.IngredientType = IngredientType;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Ingredients.IngredientType IngredientType = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_ingredient, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.item);
        name.setText(IngredientType.getName()+": " + IngredientType.getAmount().getValue() + " " + IngredientType.getAmount().getUnit());
        return convertView;
    }
}