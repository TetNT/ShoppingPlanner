package com.tetsoft.planshopping.db.planned

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlannedListDao {
    @Query("SELECT * FROM planned_lists_table ORDER BY id DESC")
    fun getAllPlannedListsDescending() : Flow<List<PlannedList>>

    @Insert
    suspend fun addPlannedList(plannedList: PlannedList)

    @Update
    suspend fun updatePlannedList(plannedList: PlannedList)

    @Delete
    suspend fun deletePlannedList(plannedList: PlannedList)

}