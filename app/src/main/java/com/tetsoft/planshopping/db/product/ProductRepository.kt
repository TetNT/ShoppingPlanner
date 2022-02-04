package com.tetsoft.planshopping.db.product

import androidx.lifecycle.LiveData

interface ProductRepository {
    val allProducts: LiveData<List<Product>>

    suspend fun addProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    suspend fun updateProduct(product: Product)
}