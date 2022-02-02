package com.tetsoft.planshopping.db.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_table")
data class Product (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val price: Double
)