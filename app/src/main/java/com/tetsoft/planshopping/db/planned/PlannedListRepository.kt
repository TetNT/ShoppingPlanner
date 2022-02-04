package com.tetsoft.planshopping.db.planned

import androidx.lifecycle.LiveData

interface PlannedListRepository {
    fun getAllLists(): LiveData<List<PlannedList>>

    suspend fun addList(plannedList: PlannedList)

    suspend fun updateList(plannedList: PlannedList)

    suspend fun deleteList(plannedList: PlannedList)
}