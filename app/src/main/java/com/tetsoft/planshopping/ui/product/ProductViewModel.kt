package com.tetsoft.planshopping.ui.product

import androidx.lifecycle.*
import com.tetsoft.planshopping.db.product.ProductRepository
import com.tetsoft.planshopping.db.product.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {

    val products = repository.allProducts

    private val mutableSelectedProduct: MutableLiveData<Product?> = MutableLiveData<Product?>()
    val editingProduct: LiveData<Product?> get() = mutableSelectedProduct

    fun selectProduct(product: Product?) {
        mutableSelectedProduct.postValue(product)
    }

    fun isEditing() : Boolean = editingProduct.value != null

    private fun addProduct(product: Product) {
        viewModelScope.launch {
            repository.addProduct(product)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            repository.deleteProduct(product)
        }
    }

    fun deleteCurrentProduct() {
        val currProd = editingProduct.value
        if (currProd != null) {
            deleteProduct(currProd)
        }
    }

    private fun updateProduct(product: Product) {
        viewModelScope.launch {
            repository.updateProduct(product)
        }
    }

    fun saveProduct(name: String, priceString: String) : Boolean {
        if (!productDataIsValid(name, priceString)) {
            return false
        }
        val edProd = editingProduct.value
        if (edProd != null) {
            updateProduct(Product(edProd.id, name, priceString.toDouble()))
        } else {
            addProduct(Product(0, name, priceString.toDouble()))
        }
        return true
    }

    private fun productDataIsValid(name: String, priceString: String) : Boolean {
        val price: Double = try {
            priceString.toDouble()
        } catch (formatEx: NumberFormatException) {
            return false
        }

        val nameLength = name.trim().length
        val priceIsPositive: Boolean = (price >= 0)
        if (nameLength == 0 || !priceIsPositive) {
            return false
        }
        return true
    }
}

@Suppress("UNCHECKED_CAST")
class ProductViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}