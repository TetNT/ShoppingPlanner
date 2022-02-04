package com.tetsoft.planshopping.ui.product

import androidx.lifecycle.*
import com.tetsoft.planshopping.db.planned.PlannedList
import com.tetsoft.planshopping.db.product.Product
import com.tetsoft.planshopping.db.selected.SelectedProduct
import com.tetsoft.planshopping.db.selected.SelectedProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductSelectionViewModel @Inject constructor(
private val selectedProductRepository: SelectedProductRepository
) : ViewModel() {

    private val _plannedList = MutableLiveData<PlannedList>()
    val currentPlannedList get() = _plannedList.value!!

    private val _selectedProduct = MutableLiveData<Product>()
    val selectedProduct : LiveData<Product> = _selectedProduct

    fun getSelectedProductsFromList(listId: Int) : LiveData<List<SelectedProduct>> {
        return selectedProductRepository.getSelectedProductsFromList(listId)
    }

    fun selectPlannedList(plannedList: PlannedList) {
        _plannedList.postValue(plannedList)
    }

    fun rememberProduct(product: Product) {
        _selectedProduct.postValue(product)
    }

    private fun addSelectedProduct(selectedProduct: SelectedProduct) {
        viewModelScope.launch {
            selectedProductRepository.addSelectedProduct(selectedProduct)
        }
    }

    fun pickCurrentProduct(amount: Int) {
        if (selectedProduct.value == null) return
        addSelectedProduct(SelectedProduct(0,
            currentPlannedList.id,
            selectedProduct.value!!,
            amount,
            false
        ))
    }

    fun removeSelectedProduct(selectedProduct: SelectedProduct) {
        viewModelScope.launch {
            selectedProductRepository.removeSelectedProduct(selectedProduct)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class ProductSelectionViewModelFactory(private val repository: SelectedProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductSelectionViewModel::class.java))
            return ProductSelectionViewModel(repository) as T
        else throw IllegalArgumentException("Unknown ViewModel class")
    }

}