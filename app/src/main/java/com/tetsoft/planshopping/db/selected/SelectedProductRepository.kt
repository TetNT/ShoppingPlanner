package com.tetsoft.planshopping.db.selected

import androidx.lifecycle.LiveData

interface SelectedProductRepository {
    fun getSelectedProductsFromList(listId: Int): LiveData<List<SelectedProduct>>

    suspend fun addSelectedProduct(selectedProduct: SelectedProduct)

    suspend fun removeSelectedProduct(selectedProduct: SelectedProduct)
    fun getSelectedProductsCount(listId: Int): LiveData<Int>
}