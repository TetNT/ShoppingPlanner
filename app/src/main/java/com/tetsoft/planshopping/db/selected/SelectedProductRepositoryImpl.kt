package com.tetsoft.planshopping.db.selected

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import javax.inject.Inject

class SelectedProductRepositoryImpl @Inject constructor(
    private val selectedProductDao: SelectedProductDao) :
    SelectedProductRepository {

    override fun getSelectedProductsFromList(listId: Int) : LiveData<List<SelectedProduct>> {
        return selectedProductDao.getSelectedProductsForTheList(listId).asLiveData()
    }

    override suspend fun addSelectedProduct(selectedProduct: SelectedProduct) {
        selectedProductDao.addSelectedProduct(selectedProduct)
    }

    override suspend fun removeSelectedProduct(selectedProduct: SelectedProduct) {
        selectedProductDao.removeSelectedProduct(selectedProduct)
    }

    override fun getSelectedProductsCount(listId: Int) : LiveData<Int> {
        return selectedProductDao.getSelectedProductsCount(listId)
    }
}