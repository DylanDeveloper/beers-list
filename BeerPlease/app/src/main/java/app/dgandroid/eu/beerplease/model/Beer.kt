package app.dgandroid.eu.beerplease.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Duilio on 22/05/2017.
 */

class Beer : Serializable {
    @SerializedName("id")
    val id: Int = 0
    @SerializedName("name")
    val name: String = ""
    @SerializedName("tagline")
    val tag: String? = null
    @SerializedName("description")
    val description: String = ""
    @SerializedName("image_url")
    val image: String = ""
    @SerializedName("contributed_by")
    val contributorsName: String = ""
    @SerializedName("first_brewed")
    val dateOfBrew: String = ""
    @SerializedName("ingredients")
    val ingredients = Ingredients()
}