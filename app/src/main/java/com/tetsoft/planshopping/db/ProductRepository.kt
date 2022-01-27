package com.tetsoft.planshopping.db

import com.tetsoft.planshopping.db.entity.Product

class ProductRepository(private val plannerDao: PlannerDao) {
    val allProducts = plannerDao.getAllProducts()

    suspend fun addProduct(product: Product) {
        plannerDao.addProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        plannerDao.deleteProduct(product)
    }

    suspend fun updateProduct(product: Product) {
        plannerDao.updateProduct(product)
    }
}