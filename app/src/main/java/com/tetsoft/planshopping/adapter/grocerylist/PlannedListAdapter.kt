package com.tetsoft.planshopping.adapter.grocerylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tetsoft.planshopping.databinding.PlannedListItemBinding
import com.tetsoft.planshopping.db.planned.PlannedList

class PlannedListAdapter(
    private val onClickListener: PlannedListItemOnClickListener
) : RecyclerView.Adapter<PlannedListAdapter.GroceryListViewHolder>() {


    private var plannedLists : List<PlannedList> = emptyList()

    private var _binding : PlannedListItemBinding? = null

    val binding get() = _binding!!

    inner class GroceryListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvListName = binding.tvProductListName
        val tvBudget = binding.tvBudget
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryListViewHolder {
        _binding = PlannedListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GroceryListViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: GroceryListViewHolder, position: Int) {
        val list : PlannedList = plannedLists[position]
        holder.tvListName.text = list.name
        holder.tvBudget.text = list.budget.toString()
        holder.itemView.setOnClickListener {
            onClickListener.onClick(list)
        }
    }

    override fun getItemCount() = plannedLists.size

    fun updateData(plannedLists: List<PlannedList>) {
        val diffCallback = GroceryListsDiffCallback(this.plannedLists, plannedLists)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        this.plannedLists = plannedLists
    }
}

open class GroceryListsDiffCallback(
    private val oldList: List<PlannedList>,
    private val newList: List<PlannedList>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList[oldItemPosition].name != newList[newItemPosition].name)
            return false
        if (oldList[oldItemPosition].budget != newList[newItemPosition].budget)
            return false
        return true
    }

}