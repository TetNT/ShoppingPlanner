package com.tetsoft.planshopping.ui.grocerylist

import androidx.lifecycle.*
import com.tetsoft.planshopping.db.GroceryListRepository
import com.tetsoft.planshopping.db.entity.GroceryList
import kotlinx.coroutines.launch

class GroceryListViewModel(private val repository: GroceryListRepository) : ViewModel() {

    val groceryLists = repository.allLists

    private val _groceryList : MutableLiveData<GroceryList?> = MutableLiveData()
    val groceryList : LiveData<GroceryList?> = _groceryList

    fun addGroceryList(groceryList: GroceryList) {
        viewModelScope.launch {
            repository.addList(groceryList)
        }
    }

    fun selectGroceryList(groceryList: GroceryList) {
        _groceryList.postValue(groceryList)
    }
}

class GroceryListViewModelFactory(private val repository: GroceryListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroceryListViewModel::class.java))
            return GroceryListViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}