package com.tetsoft.planshopping.ui.planned

import androidx.lifecycle.*
import com.tetsoft.planshopping.db.planned.PlannedList
import com.tetsoft.planshopping.db.planned.PlannedListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlannedListViewModel @Inject constructor(
    private val repository: PlannedListRepository
    ) : ViewModel() {

    val groceryLists = repository.allLists

    private val _plannedList : MutableLiveData<PlannedList?> = MutableLiveData()
    val plannedList : LiveData<PlannedList?> = _plannedList

    fun addPlannedList(plannedList: PlannedList) {
        viewModelScope.launch {
            repository.addList(plannedList)
        }
    }

    fun updatePlannedList(plannedList: PlannedList) {
        viewModelScope.launch {
            repository.updateList(plannedList)
        }
    }

    fun deletePlannedList(plannedList: PlannedList) {
        viewModelScope.launch {
            repository.deleteList(plannedList)
        }
    }

    fun selectPlannedList(plannedList: PlannedList) {
        _plannedList.postValue(plannedList)
    }
}

@Suppress("UNCHECKED_CAST")
class PlannedListViewModelFactory(
    private val repository: PlannedListRepository
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlannedListViewModel::class.java))
            return PlannedListViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}