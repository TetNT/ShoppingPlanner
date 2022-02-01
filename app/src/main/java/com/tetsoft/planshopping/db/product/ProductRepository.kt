package com.tetsoft.planshopping.db.product

import androidx.lifecycle.asLiveData

class ProductRepository(private val productDao: ProductDao) {
    val allProducts = productDao.getAllProducts().asLiveData()

    suspend fun addProduct(product: Product) {
        productDao.addProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }

    suspend fun updateProduct(product: Product) {
        productDao.updateProduct(product)
    }
}