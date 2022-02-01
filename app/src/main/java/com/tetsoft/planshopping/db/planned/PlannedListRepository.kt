package com.tetsoft.planshopping.db.planned

import androidx.lifecycle.asLiveData

class PlannedListRepository(private val plannedListDao: PlannedListDao) {

    val allLists = plannedListDao.getAllPlannedListsDescending().asLiveData()

    suspend fun addList(plannedList: PlannedList) {
        plannedListDao.addPlannedList(plannedList)
    }

    suspend fun updateList(plannedList: PlannedList) {
        plannedListDao.updatePlannedList(plannedList)
    }

    suspend fun deleteList(plannedList: PlannedList) {
        plannedListDao.deletePlannedList(plannedList)
    }
}