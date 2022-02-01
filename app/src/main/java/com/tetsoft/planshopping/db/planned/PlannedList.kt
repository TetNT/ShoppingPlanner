package com.tetsoft.planshopping.db.planned

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planned_lists_table")
data class PlannedList (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val budget: Double
)