package com.tetsoft.planshopping.db.selected

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData

class SelectedProductRepository(private val selectedProductDao: SelectedProductDao) {
    fun getSelectedProductsFromList(listId: Int) : LiveData<List<SelectedProduct>> {
        return selectedProductDao.getSelectedProductsForTheList(listId).asLiveData()
    }

    suspend fun addSelectedProduct(selectedProduct: SelectedProduct) {
        selectedProductDao.addSelectedProduct(selectedProduct)
    }

    suspend fun removeSelectedProduct(selectedProduct: SelectedProduct) {
        selectedProductDao.removeSelectedProduct(selectedProduct)
    }

    fun getSelectedProductsCount(listId: Int) : Int {
        return selectedProductDao.getSelectedProductsCount(listId)
    }
}