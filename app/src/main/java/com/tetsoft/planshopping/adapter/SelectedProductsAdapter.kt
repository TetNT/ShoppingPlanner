package com.tetsoft.planshopping.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tetsoft.planshopping.R
import com.tetsoft.planshopping.db.product.Product
import com.tetsoft.planshopping.db.selected.SelectedProduct

class SelectedProductsAdapter : RecyclerView.Adapter<SelectedProductsAdapter.SelectionViewHolder>() {

    private var selectionList = emptyList<SelectedProduct>()

    inner class SelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBoxSelected: CheckBox = itemView.findViewById(R.id.checkbox_selected)
        val quantity: EditText = itemView.findViewById(R.id.et_product_selection_quantity)
        val price: EditText = itemView.findViewById(R.id.et_product_selection_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectionViewHolder {
        return SelectionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.product_selection_item,
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: SelectionViewHolder, position: Int) {
        val product: Product = selectionList[position].product
        holder.checkBoxSelected.text = product.name
        holder.price.setText(product.price.toString())
        holder.quantity.setText((selectionList[position].amount).toString())
    }

    override fun getItemCount(): Int {
        return selectionList.size
    }

    fun updateData(selectionList: List<SelectedProduct>) {
        val diffUtilCallback = SelectedProductsDiffCallback(this.selectionList, selectionList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        diffResult.dispatchUpdatesTo(this)
        this.selectionList = selectionList
    }
}

class SelectedProductsDiffCallback(
    private val oldList: List<SelectedProduct>,
    private val newList: List<SelectedProduct>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList[oldItemPosition] != newList[newItemPosition]) return false
        return true
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList[oldItemPosition] != newList[newItemPosition]) return false
        return true
    }

}