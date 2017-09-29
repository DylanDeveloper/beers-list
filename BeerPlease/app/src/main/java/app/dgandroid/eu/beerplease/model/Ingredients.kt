package app.dgandroid.eu.beerplease.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Duilio on 22/05/2017.
 */

class Ingredients : Serializable {
    @SerializedName("malt")
    val malt = arrayListOf<IngredientType>()
    @SerializedName("hops")
    val hops = arrayListOf<IngredientType>()

    inner class IngredientType : Serializable {
        @SerializedName("name")
        val name: String = ""
        @SerializedName("amount")
        val amount = Amount()

        inner class Amount : Serializable {
            @SerializedName("value")
            val value: Float = 0.toFloat()
            @SerializedName("unit")
            val unit: String = ""
        }
    }
}