package com.tetsoft.planshopping.adapter.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tetsoft.planshopping.databinding.ProductItemBinding
import com.tetsoft.planshopping.db.entity.Product

class ProductAdapter(
    private val onClickListener: ProductItemOnClickListener
    ) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var _binding : ProductItemBinding? = null
    private val binding get() = _binding!!

    private var productsList = emptyList<Product>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val productName = binding.tvProductName
        val productPrice = binding.tvProductPrice

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product : Product = productsList[position]
        holder.productName.text = product.name
        holder.productPrice.text = product.price.toString()
        holder.itemView.setOnClickListener {
            onClickListener.onClick(product)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    fun updateData(productsList: List<Product>) {
        val diffCallback = ProductListDiffCallback(this.productsList, productsList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        this.productsList = productsList
    }
}

open class ProductListDiffCallback(
    private val oldProductList: List<Product>,
    private val newProductList: List<Product>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldProductList.size
    }

    override fun getNewListSize(): Int {
        return newProductList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldProductList[oldItemPosition] == newProductList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldProductList[oldItemPosition].name != newProductList[newItemPosition].name)
            return false
        if (oldProductList[oldItemPosition].price != newProductList[newItemPosition].price)
            return false
        return true
    }

}