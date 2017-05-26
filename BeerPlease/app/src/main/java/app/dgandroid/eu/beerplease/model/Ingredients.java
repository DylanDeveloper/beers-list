package app.dgandroid.eu.beerplease.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Duilio on 22/05/2017.
 */

public class Ingredients implements Serializable {

    @SerializedName("malt")
    private ArrayList<IngredientType> malt;
    @SerializedName("hops")
    private ArrayList<IngredientType> hops;

    public ArrayList<IngredientType> getHops() {
        return hops;
    }

    public ArrayList<IngredientType> getMalt() {
        return malt;
    }

    public class IngredientType implements Serializable {
        @SerializedName("name")
        private String name;
        @SerializedName("amount")
        private Amount amount;

        public Amount getAmount() {
            return amount;
        }
        public String getName() {
            return name;
        }

        public class Amount implements Serializable {
            @SerializedName("value")
            private float value;
            @SerializedName("unit")
            private String unit;

            public float getValue() {
                return value;
            }
            public String getUnit() {
                return unit;
            }
        }
    }
}