package app.dgandroid.eu.beerplease.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by Duilio on 22/05/2017.
 */

public class Beer implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("tagline")
    private String tag;
    @SerializedName("description")
    private String description;
    @SerializedName("image_url")
    private String image;
    @SerializedName("contributed_by")
    private String contributorsName;
    @SerializedName("first_brewed")
    private String dateOfBrew;
    @SerializedName("ingredients")
    private Ingredients ingredients;

    public String getName() {
        return name;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public int getId() {
        return id;
    }

    public String getContributorsName() {
        return contributorsName;
    }

    public String getDateOfBrew() {
        return dateOfBrew;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getTag() {
        return tag;
    }
}