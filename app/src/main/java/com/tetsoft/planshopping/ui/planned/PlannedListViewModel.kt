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

    val groceryLists = repository.getAllLists()

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
