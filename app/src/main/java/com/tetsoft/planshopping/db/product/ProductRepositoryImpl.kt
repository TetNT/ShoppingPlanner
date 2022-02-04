package com.tetsoft.planshopping.db.product

import androidx.lifecycle.asLiveData
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productDao: ProductDao) :
    ProductRepository {

    override val allProducts = productDao.getAllProducts().asLiveData()

    override suspend fun addProduct(product: Product) {
        productDao.addProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }

    override suspend fun updateProduct(product: Product) {
        productDao.updateProduct(product)
    }
}