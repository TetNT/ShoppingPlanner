package com.tetsoft.planshopping.ui.product

import androidx.lifecycle.*
import com.tetsoft.planshopping.OperationType
import com.tetsoft.planshopping.db.ProductRepository
import com.tetsoft.planshopping.db.entity.Product
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val products = repository.allProducts

    private val mutableSelectedProduct: MutableLiveData<Product?> = MutableLiveData<Product?>()
    val selectedProduct: LiveData<Product?> get() = mutableSelectedProduct

    fun selectProduct(product: Product?) {
        mutableSelectedProduct.postValue(product)
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            repository.addProduct(product)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            repository.deleteProduct(product)
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            repository.updateProduct(product)
        }
    }
}

class ProductViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}