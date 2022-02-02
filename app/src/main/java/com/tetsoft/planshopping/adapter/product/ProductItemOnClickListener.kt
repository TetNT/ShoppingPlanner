package com.tetsoft.planshopping.adapter.product

import com.tetsoft.planshopping.db.product.Product

class ProductItemOnClickListener(val clickListener: (product: Product) -> Unit) {
    fun onClick(product: Product) {
        clickListener(product)
    }
}