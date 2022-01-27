package com.tetsoft.planshopping.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_list_table")
data class GroceryList (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val budget: Double
)