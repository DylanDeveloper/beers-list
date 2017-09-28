package app.dgandroid.eu.beerplease.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import java.util.HashMap
import app.dgandroid.eu.beerplease.R
import app.dgandroid.eu.beerplease.model.Ingredients
import kotlinx.android.synthetic.main.header_ingredients.view.*
import kotlinx.android.synthetic.main.item_ingredient.view.*

/**
 * Created by Duilio on 27/09/2017.
 */

class IngredientsAdapter(private val mContext: Context, private val mListDataHeader: List<String>, private val mListDataChild: HashMap<String, List<Ingredients.IngredientType>>) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return this.mListDataHeader.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.mListDataChild[this.mListDataHeader[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return this.mListDataHeader[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.mListDataChild[this.mListDataHeader[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        val view: View? = when (convertView) {
            null -> LayoutInflater.from(mContext).inflate(R.layout.header_ingredients, parent, false)
            else -> convertView
        }
        val headerTitle : String = getGroup(groupPosition) as String
        when (headerTitle) {
            "Malts" -> view?.imageIngredient!!.setImageResource(R.drawable.malts)
            "Hops" -> view?.imageIngredient!!.setImageResource(R.drawable.hops)
        }
        view?.ingredientHeader?.text = "$headerTitle :"
        return view!!
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        val view: View? = when (convertView) {
            null -> LayoutInflater.from(mContext).inflate(R.layout.item_ingredient, parent, false)
            else -> convertView
        }
        val ingredientType = getChild(groupPosition, childPosition) as Ingredients.IngredientType
        val text = "${ingredientType.name} : ${ingredientType.amount?.value} ${ingredientType.amount?.unit}"
        view!!.itemIngredient.text = text
        return view!!
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }
}