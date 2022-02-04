package com.tetsoft.planshopping.db.planned

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import javax.inject.Inject

class PlannedListRepositoryImpl @Inject constructor(private val plannedListDao: PlannedListDao) :
    PlannedListRepository {

    override fun getAllLists(): LiveData<List<PlannedList>> {
        return plannedListDao.getAllPlannedListsDescending().asLiveData()
    }

    override suspend fun addList(plannedList: PlannedList) {
        plannedListDao.addPlannedList(plannedList)
    }

    override suspend fun updateList(plannedList: PlannedList) {
        plannedListDao.updatePlannedList(plannedList)
    }

    override suspend fun deleteList(plannedList: PlannedList) {
        plannedListDao.deletePlannedList(plannedList)
    }
}